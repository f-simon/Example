package com.fdc.mtrg.network.token.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.ms.TokenizeRequest;
import com.fdc.mtrg.network.token.ms.TokenizeResponse;
import com.fdc.mtrg.network.token.ms.error.Errors;
import com.fdc.mtrg.network.token.ms.search.SearchTokensResponseSchema;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import com.fdc.util.exception.FdcRuntimeException;
import com.fdc.util.exception.model.NVP;
import com.fdc.util.exception.types.FdcSystemException;
import com.fdc.util.logging.SimpleAroundLog;
import com.mastercard.developer.encryption.EncryptionException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TokenizeService extends NetworkTokensService {

    private static final Logger logger = LoggerFactory.getLogger(TokenizeService.class);

    @Autowired
    private ObjectMapper objectMapper;

    @SimpleAroundLog
    @ServiceActivator
    @HystrixCommand(commandKey = "mc-provision-command", threadPoolKey = "mc-provision-thread-pool", ignoreExceptions = FdcRuntimeException.class)
    public TokenizeResponse tokenize(@Header(Constants.PARTNER_ID) final String pmerchantId,
                                     TokenizeRequest pRequestMessage) throws FdcSystemException, FdcException {

        logger.debug("Request received @ tokenize API for merchant Partner {} and request {} ", pmerchantId, pRequestMessage);

        final TokenizeResponse tokenizeResponse;
        try {
            HttpEntity<String> tokenizationRequest = new HttpEntity<String>(objectMapper.writeValueAsString(pRequestMessage), getHttpHeaders());
            String tokenizeResponseStr = this.restTemplate.exchange(getUri(Constants.TOKENIZATION_URI), HttpMethod.POST, tokenizationRequest, String.class).getBody();
            tokenizeResponse = this.decrypt(tokenizeResponseStr, TokenizeResponse.class);
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

        if (tokenizeResponse == null) {
            logger.error("Getting null response from MDES for Tokenization API");
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        }

        if (null == tokenizeResponse.getErrorCode())
            return tokenizeResponse;
        else {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(tokenizeResponse.getErrorCode(), tokenizeResponse.getErrorCode(), tokenizeResponse.getErrorDescription()));
            List<NVP> nvps = new ArrayList<>();
            if (null != tokenizeResponse.getErrors() && tokenizeResponse.getErrors().size() > 0) {
                for (Errors error : tokenizeResponse.getErrors()) {
                    NVP nvp = new NVP();
                    nvp.setName(error.getReasonCode());
                    nvp.setValue(error.getDescription());
                    nvps.add(nvp);
                }
            }
            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors, ApplicationError.INVALID_REQUEST.getErrorDescription(), nvps);
        }
    }

    @SimpleAroundLog
    @ServiceActivator
    @HystrixCommand(commandKey = "mc-search-command", threadPoolKey = "mc-search-thread-pool")
    public SearchTokensResponseSchema searchTokens(@Header(Constants.PARTNER_ID) final String pmerchantId,
                                                   TokenizeRequest pRequestMessage) throws FdcException {

        logger.debug("Request received @ searchTokens API for merchant Partner {} and request {} ", pmerchantId, pRequestMessage);

        HttpEntity<TokenizeRequest> request = new HttpEntity<TokenizeRequest>(pRequestMessage, getHttpHeaders());
        SearchTokensResponseSchema responsePayload;
        try {
            responsePayload = this.restTemplate.exchange(getUri(Constants.SEARCH_TOKENS_URI), HttpMethod.POST, request, SearchTokensResponseSchema.class).getBody();

        } catch (final RestClientException rce) {
            logger.error(rce.getMessage(), rce);
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        }

        if (responsePayload == null) {
            logger.error("Getting null response from MDES for Search API");
            throw new FdcException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        }

        if (null == responsePayload.getErrorCode())
            return responsePayload;
        else {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(responsePayload.getErrorCode(), responsePayload.getErrorCode(), responsePayload.getErrorDescription()));
            List<NVP> nvps = new ArrayList<>();
            if (null != responsePayload.getErrors() && responsePayload.getErrors().size() > 0) {
                for (Errors error : responsePayload.getErrors()) {
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
