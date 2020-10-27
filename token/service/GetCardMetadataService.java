package com.fdc.mtrg.network.token.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.ms.AssetResponse;
import com.fdc.mtrg.network.token.ms.error.Errors;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import com.fdc.util.exception.FdcRuntimeException;
import com.fdc.util.exception.model.NVP;
import com.fdc.util.exception.types.FdcSystemException;
import com.fdc.util.logging.SimpleAroundLog;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;


@Service
public class GetCardMetadataService extends NetworkTokensService {

    private static final Logger logger = LoggerFactory.getLogger(GetCardMetadataService.class);

    @SimpleAroundLog
    @ServiceActivator
    @HystrixCommand(commandKey = "mc-getasset-command", threadPoolKey = "mc-getasset-thread-pool", ignoreExceptions = FdcRuntimeException.class)
    public Message<AssetResponse> getCardMetadata(@Header(Constants.PARTNER_ID) final String merchantId,
                                                  @Header(Constants.ASSET_ID) final String assetId) throws FdcException, JsonProcessingException {
        logger.debug("Request received @ getCardMetadataService API for merchant Partner {} and request {} ", merchantId, assetId);

        HttpEntity<String> cardMetadataRequest = new HttpEntity<String>(getHttpHeaders());

        final StringBuilder sb = new StringBuilder(applicationProperties.getServiceUrl());
        sb.append(Constants.CARD_METADATA_URI);
        sb.append(assetId);

        logger.debug("outbound url: {} ", sb.toString());
        
        final AssetResponse result;
        try {
            result = this.restTemplate.exchange(sb.toString(), HttpMethod.GET, cardMetadataRequest, AssetResponse.class).getBody();
        } catch (final RestClientException rce) {
            logger.error(rce.getMessage(), rce);
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        }

        if (result == null) {
            logger.error("Getting null response from MDES for MetaData API");
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        }

        if (null == result.getErrorCode())
            return MessageBuilder.withPayload(result).build();
        else {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(result.getErrorCode(), result.getErrorCode(), result.getErrorDescription()));
            List<NVP> nvps = new ArrayList<>();
            if (null != result.getErrors() && result.getErrors().size() > 0) {
                for (Errors error : result.getErrors()) {
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