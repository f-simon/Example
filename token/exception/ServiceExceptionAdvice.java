package com.fdc.mtrg.network.token.exception;

import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.util.exception.FdcException;
import com.fdc.util.exception.handler.FdcExceptionHandler;
import com.fdc.util.exception.model.FdcDeveloperInfo;
import com.fdc.util.exception.model.FdcExceptionResponse;
import com.fdc.util.exception.model.FdcFieldError;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ServiceExceptionAdvice extends FdcExceptionHandler {


    /**
     * Convert a predefined exception to an HTTP Status code and specify the
     * name of a specific view that will be used to display the error.
     *
     * @return Exception view.
     */

    @ExceptionHandler({SpelEvaluationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FdcExceptionResponse springIntegrationFlowException(Exception ex) throws FdcException {
        List<FdcFieldError> fieldErrorList = new ArrayList<>();
        FdcFieldError fieldError = new FdcFieldError( "Operation", "Invalid Operation type.");
        fieldErrorList.add(fieldError);
        final FdcExceptionResponse response = new FdcExceptionResponse();
        response.setCode(ApplicationError.INVALID_REQUEST.getErrorCode());
        response.setMessage(ApplicationError.INVALID_REQUEST.getErrorDescription());
        response.setCategory(ApplicationError.INVALID_REQUEST.getCategory());
        FdcDeveloperInfo developerInfo = new FdcDeveloperInfo(ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrorList);
        response.setDeveloperInfo(developerInfo);
        return response;
    }

    @ExceptionHandler({HystrixRuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FdcExceptionResponse databaseError(HystrixRuntimeException ex) {
        final FdcExceptionResponse response = new FdcExceptionResponse();
        response.setCode(ApplicationError.SERVICE_TIMEOUT.getErrorCode());
        response.setMessage(ApplicationError.SERVICE_TIMEOUT.getErrorDescription());
        response.setCategory(ApplicationError.SERVICE_TIMEOUT.getCategory());
        FdcDeveloperInfo developerInfo = new FdcDeveloperInfo(ApplicationError.SERVICE_TIMEOUT.getErrorDescription(), null);
        response.setDeveloperInfo(developerInfo);
        return response;
    }
}
