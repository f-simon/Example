package com.fdc.mtrg.network.token.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.ms.TransactRequest;
import com.fdc.mtrg.network.token.ms.TransactResponse;
import com.fdc.mtrg.network.token.ms.error.Errors;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import com.fdc.util.exception.FdcRuntimeException;
import com.fdc.util.exception.model.NVP;
import com.fdc.util.exception.types.FdcSystemException;
import com.mastercard.developer.encryption.EncryptionException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactService extends NetworkTokensService {

    private static final Logger logger = LoggerFactory.getLogger(TransactService.class);

    @Autowired
    @Qualifier("mcRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @ServiceActivator
    @HystrixCommand(commandKey = "mc-cryptogram-command", threadPoolKey = "mc-cryptogram-thread-pool", ignoreExceptions = FdcRuntimeException.class)
    public Message<TransactResponse> doOutboundServiceCall(@Header(Constants.PARTNER_ID) final String pTokenReferenceId,
                                                           TransactRequest pRequestMessage) throws FdcSystemException, FdcException {
        TransactResponse transactResponse;

        try {
            HttpEntity<String> transactRequest = new HttpEntity<String>(objectMapper.writeValueAsString(pRequestMessage), getHttpHeaders());
            String transactResponseStr = this.restTemplate.exchange(getUri(Constants.TRANSACT_URI), HttpMethod.POST, transactRequest, String.class).getBody();
            transactResponse = decrypt(transactResponseStr, TransactResponse.class);
        } catch (final RestClientException rce) {
            logger.error(rce.getMessage(), rce);
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        } catch (EncryptionException ee) {
            logger.error(ee.getMessage(), ee);
            throw new FdcException(ApplicationError.TRANSACT_FAILED.getErrorCode(), ApplicationError.TRANSACT_FAILED.getErrorDescription());
        } catch (IOException ie) {
            logger.error(ie.getMessage(), ie);
            throw new FdcException(ApplicationError.IO_EXCEPTION.getErrorCode(), ApplicationError.IO_EXCEPTION.getErrorDescription());
        }

        if (transactResponse == null) {
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        }

        if (null == transactResponse.getErrorCode())
            MessageBuilder.withPayload(transactResponse).build();
        else {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(transactResponse.getErrorCode(), transactResponse.getErrorCode(), transactResponse.getErrorDescription()));
            List<NVP> nvps = new ArrayList<>();
            if (null != transactResponse.getErrors() && transactResponse.getErrors().size() > 0) {
                for (Errors error : transactResponse.getErrors()) {
                    NVP nvp = new NVP();
                    nvp.setName(error.getReasonCode());
                    nvp.setValue(error.getDescription());
                    nvps.add(nvp);
                }
            }
            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors, ApplicationError.INVALID_REQUEST.getErrorDescription(), nvps);
        }

        return MessageBuilder.withPayload(transactResponse).build();
    }
}