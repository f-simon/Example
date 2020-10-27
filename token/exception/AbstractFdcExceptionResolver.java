package com.fdc.mtrg.network.token.exception;

import com.fdc.mtrg.network.token.error.FdcServiceError;
import com.fdc.util.exception.FdcExceptionResolver;
import com.fdc.util.exception.IFdcException;
import com.fdc.util.exception.model.FdcDeveloperInfo;
import com.fdc.util.exception.model.FdcExceptionResponse;
import com.fdc.util.exception.model.FdcFieldError;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public abstract class AbstractFdcExceptionResolver implements FdcExceptionResolver {
    public AbstractFdcExceptionResolver() {
    }

    public abstract FdcServiceError getServiceError(String var1, HttpServletRequest var2);

    public FdcExceptionResponse doResolveFdcException(IFdcException exception, HttpServletRequest servletRequest) {
        FdcServiceError serviceError = this.getServiceError(exception.getCode(), servletRequest);
        FdcExceptionResponse exceptionResponse = new FdcExceptionResponse();
        exceptionResponse.setCategory(serviceError.getCategory());
        exceptionResponse.setCode(serviceError.getErrorCode());
        exceptionResponse.setMessage(serviceError.getErrorDescription());
        FdcDeveloperInfo developerInfo = new FdcDeveloperInfo();
        developerInfo.setDeveloperMessage(serviceError.getDeveloperMessage());
        developerInfo.setMoreInfo(serviceError.getMoreInfo());
        if (CollectionUtils.isNotEmpty(exception.getFieldError())) {
            List<FdcFieldError> fieldErrors = new ArrayList();
            Iterator var7 = exception.getFieldError().iterator();

            while (var7.hasNext()) {
                FieldError error = (FieldError) var7.next();
                fieldErrors.add(new FdcFieldError(error.getField(), error.getDefaultMessage()));
            }

            developerInfo.setFieldError(fieldErrors);
        }

        exceptionResponse.setDeveloperInfo(developerInfo);
        return exceptionResponse;
    }
}

