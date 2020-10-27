package com.fdc.mtrg.network.token.validator;

import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetTokenFilter {
    private static final Logger logger = LoggerFactory.getLogger(GetTokenFilter.class);


    @Filter
    public boolean doValidateRequest(@Header(Constants.PARTNER_ID) final String merchantId,
                                     @Header(Constants.TOKEN_REFERENCE_ID) final String tokenReferenceId) throws FdcException {
        logger.debug("Request received @ doValidateRequest API for merchant Partner {} and tokenReferenceId {} ", merchantId, tokenReferenceId);

        List<FieldError> fieldErrors = new ArrayList<>();

        if (StringUtils.isBlank(tokenReferenceId)) {
            fieldErrors.add(new FieldError("Invalid URL : ", "tokenReferenceId", "tokenReferenceId should be part of the URL "));
            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(),
                    fieldErrors);
        }

        return true;
    }

}
