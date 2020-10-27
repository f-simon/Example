package com.fdc.mtrg.network.token.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.network.token.config.ApplicationProperties;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.ms.DeleteRequestSchema;
import com.fdc.mtrg.network.token.ms.DeleteResponseSchema;
import com.fdc.mtrg.network.token.ms.error.Errors;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import com.fdc.util.exception.FdcRuntimeException;
import com.fdc.util.exception.model.NVP;
import com.fdc.util.exception.types.FdcSystemException;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateLifecycleDeleteService {
    private static final Logger logger = LoggerFactory.getLogger(CreateLifecycleDeleteService.class);

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    @Qualifier("mcRestTemplate")
    private RestTemplate restTemplate;


    @ServiceActivator
    @HystrixCommand(commandKey = "mc-deleteService-command", threadPoolKey = "mc-deleteService-thread-pool", ignoreExceptions = FdcRuntimeException.class)
    public DeleteResponseSchema doOutboundServiceCall(@Header(Constants.PARTNER_ID) final String merchantId,
                                                      DeleteRequestSchema lifeCycleMessage) throws FdcSystemException, FdcException {

        logger.debug("Request received @ doOutboundServiceCall API for merchant Action {} and request {} ", merchantId, lifeCycleMessage);

        final StringBuilder sb = new StringBuilder(applicationProperties.getServiceUrl());
        sb.append(Constants.DELETE_URI);



        final HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.ACCCEPT, Constants.CONTENT_TYPE_JSON);
        headers.set(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);

        HttpEntity<DeleteRequestSchema> supendRequestEntity = new HttpEntity<DeleteRequestSchema>(lifeCycleMessage, headers);

        final DeleteResponseSchema returnValue;
        try {
            returnValue = this.restTemplate.exchange(sb.toString(), HttpMethod.POST, supendRequestEntity, DeleteResponseSchema.class).getBody();
        } catch (final RestClientException e) {
            logger.error(e.getMessage(), e);
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        }

        if (returnValue == null) {
            logger.error("Getting null response from MDES for Delete API");
            throw new FdcSystemException(ApplicationError.SERVICE_UNAVAILABLE.getErrorCode(), ApplicationError.SERVICE_UNAVAILABLE.getErrorDescription());
        }

        if (null == returnValue.getErrorCode())
            return returnValue;
        else {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(returnValue.getErrorCode(), returnValue.getErrorCode(), returnValue.getErrorDescription()));
            List<NVP> nvps = new ArrayList<>();
            if (null != returnValue.getErrors() && returnValue.getErrors().size() > 0) {
                for (Errors error : returnValue.getErrors()) {
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
