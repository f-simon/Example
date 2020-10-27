package com.fdc.mtrg.network.token.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.api.CryptoGramRequest;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;


@Service
public class TransactFilter {

    private static final Logger logger = LoggerFactory.getLogger(TransactFilter.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Filter
    public boolean doValidateRequest(@Header(Constants.PARTNER_ID) final String merchantId,
                                     @Header(Constants.TOKEN_REFERENCE_ID) final String pTokenReferenceId,
                                     Message<CryptoGramRequest> pRequestMessage) throws FdcException, JsonProcessingException {

        List<FieldError> fieldErrors = new ArrayList<>();

        CryptoGramRequest payload = pRequestMessage.getPayload();

        if (StringUtils.isBlank(pTokenReferenceId)) {
            fieldErrors.add(new FieldError("Invalid attribute : TokenReferenceId ", "TokenReferenceId", "Invalid attribute : " + pTokenReferenceId));

            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors, ApplicationError.INVALID_REQUEST.getCategory(), null);
        }

        if ((null == payload) || (null == payload.getTransactionType()) || StringUtils.isBlank(payload.getTransactionType().getValue())) {
            fieldErrors.add(new FieldError(CryptoGramRequest.class.getName(), Constants.TRANSACT_REQUEST_PAYLOAD, Constants.NULL_PAYLOAD));

            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors, ApplicationError.INVALID_REQUEST.getCategory(), null);
        }

        return true;
    }
}