package com.fdc.mtrg.network.token.service;

import com.fdc.mtrg.network.token.ms.TaskStatusRequest;
import com.fdc.mtrg.network.token.ms.TaskStatusResponse;
import com.fdc.mtrg.network.token.util.Constants;
import com.mastercard.developer.encryption.EncryptionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class GetTaskStatusService extends NetworkTokensService {
    private static final Logger logger = LoggerFactory.getLogger(GetTaskStatusService.class);

    @Autowired
    @Qualifier("mcRestTemplate")
    private RestTemplate restTemplate;


    @ServiceActivator
    public TaskStatusResponse doOutboundServiceCall(@Header(Constants.PARTNER_ID) final String pmerchantId,
                                                    TaskStatusRequest pRequestMessage) throws EncryptionException, IOException {
        HttpEntity<TaskStatusRequest> getTaskStatusRequest = new HttpEntity<TaskStatusRequest>(pRequestMessage, getHttpHeaders());
        TaskStatusResponse taskStatusResponse;

        try {
            taskStatusResponse = this.restTemplate.exchange(getUri(Constants.GET_TASK_STATUS_URI), HttpMethod.POST, getTaskStatusRequest, TaskStatusResponse.class).getBody();
        } catch (final RestClientException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        return taskStatusResponse;
    }
}