package com.fdc.mtrg.network.token.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.api.UpdateTokenRequest;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CreateLifecycleSuspendFilter {
    private static final Logger logger = LoggerFactory.getLogger(CreateLifecycleSuspendFilter.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Filter
    public boolean doValidateRequest(@Header(Constants.PARTNER_ID) final String merchantId,
                                     @Header(Constants.TOKEN_REFERENCE_ID) final String tokenReferenceId,
                                     Message<UpdateTokenRequest> updateTokenRequest) throws FdcException, JsonProcessingException {

        logger.debug("Request received @ doValidateRequest API for merchant Partner {} :  tokenReferenceId {} and request {} ", merchantId, tokenReferenceId, objectMapper.writeValueAsString(updateTokenRequest.getPayload()));

        List<String> validTspIds = Arrays.asList("501", "400");

        UpdateTokenRequest payload = updateTokenRequest.getPayload();
        List<FieldError> fieldErrors = new ArrayList<>();

        if (null == payload.getOperation() || null == payload.getUpdateReason()) {
            fieldErrors.add(new FieldError("Invalid attribute : ", "Operation/Reason", "Operation/Reason is required"));
            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors);
        }

        if (null != payload.getUpdateReason()) {
            if (null == payload.getUpdateReason().getCausedBy()) {
                fieldErrors.add(new FieldError("Invalid attribute : ", "causedBy", "Invalid cause"));
            }

            if (null == payload.getUpdateReason().getReasonCode()) {
                fieldErrors.add(new FieldError("Invalid attribute : ", "reasonCode", "Invalid reasonCode"));
            }

            if (null == payload.getUpdateReason().getTspId()) {
                fieldErrors.add(new FieldError("Invalid attribute : ", "tspId", "Invalid tspId"));
            }else {
                if (!payload.getUpdateReason().getTspId().equals(validTspIds.get(0))){
                    fieldErrors.add(new FieldError("Invalid attribute : ", "tspId", "Invalid tspId"));
                }
            }

            if (!fieldErrors.isEmpty() && fieldErrors.size() > 0) {
                throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(),
                        fieldErrors);
            }
        }
        return true;
    }
}
