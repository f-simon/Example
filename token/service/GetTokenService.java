package com.fdc.mtrg.network.token.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.network.token.config.ApplicationProperties;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.ms.GetTokenRequestSchema;
import com.fdc.mtrg.network.token.ms.GetTokenResponseSchema;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetTokenService extends NetworkTokensService {
    private static final Logger logger = LoggerFactory.getLogger(GetTokenService.class);

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    @Qualifier("mcRestTemplate")
    private RestTemplate restTemplate;

    @ServiceActivator
    @HystrixCommand(commandKey = "mc-getTokenService-command", threadPoolKey = "mc-getTokenService-thread-pool", ignoreExceptions = FdcRuntimeException.class)
    public GetTokenResponseSchema doOutboundServiceCall(@Header(Constants.PARTNER_ID) final String merchantId,
                                                        GetTokenRequestSchema getTokenRequestSchema) throws FdcSystemException, FdcException {

        logger.debug("Request received @ doOutboundServiceCall API for merchant merchantId {} and request {} ", merchantId, getTokenRequestSchema);

        final StringBuilder sb = new StringBuilder(applicationProperties.getServiceUrl());
        sb.append(Constants.GETTOKEN);

        logger.debug("outbound url: {} ", sb.toString());

        final HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.ACCCEPT, Constants.CONTENT_TYPE_JSON);
        headers.set(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);

        HttpEntity<GetTokenRequestSchema> getTokenRequestEntity = new HttpEntity<GetTokenRequestSchema>(getTokenRequestSchema, headers);

        final String returnValue;
        final GetTokenResponseSchema tokenResponseSchema;
        try {
            returnValue = this.restTemplate.exchange(sb.toString(), HttpMethod.POST, getTokenRequestEntity, String.class).getBody();
            tokenResponseSchema = this.decrypt(returnValue, GetTokenResponseSchema.class);
        } catch (final RestClientException rce) {
            logger.error(rce.getMessage(), rce);
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        } catch (EncryptionException ee) {
            logger.error(ee.getMessage(), ee);
            throw new FdcException(ApplicationError.TOKENIZATION_FAILED.getErrorCode(), ApplicationError.TOKENIZATION_FAILED.getErrorDescription());
        } catch (IOException ie) {
            logger.error(ie.getMessage(), ie);
            throw new FdcException(ApplicationError.TOKENIZATION_FAILED.getErrorCode(), ApplicationError.TOKENIZATION_FAILED.getErrorDescription());
        }

        if (tokenResponseSchema == null) {
            logger.error("Getting null response from MDES for GetToken API");
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        }

        if (null == tokenResponseSchema.getErrorCode())
            return tokenResponseSchema;
        else {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(tokenResponseSchema.getErrorCode(), tokenResponseSchema.getErrorCode(), tokenResponseSchema.getErrorDescription()));
            List<NVP> nvps = new ArrayList<>();
            if (null != tokenResponseSchema.getErrors() && tokenResponseSchema.getErrors().size() > 0) {
                for (Errors error : tokenResponseSchema.getErrors()) {
                    NVP nvp = new NVP();
                    nvp.setName(error.getReasonCode());
                    nvp.setValue(error.getDescription());
                    nvps.add(nvp);
                }
            }
            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors, ApplicationError.INVALID_REQUEST.getErrorDescription(), nvps);
        }
    }
}
