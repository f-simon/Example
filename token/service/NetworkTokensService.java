package com.fdc.mtrg.network.token.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.network.token.config.ApplicationProperties;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.encryption.FieldLevelEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

import static com.fdc.mtrg.network.token.util.FileUtils.buildConfig;

@Service
public class NetworkTokensService {
    private static final Logger logger = LoggerFactory.getLogger(NetworkTokensService.class);

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected ApplicationProperties applicationProperties;

    @Autowired
    @Qualifier("mcRestTemplate")
    protected RestTemplate restTemplate;

    @Autowired
    @Qualifier("decryptionKey")
    protected PrivateKey decryptionKey;

    @Autowired
    @Qualifier("encryptionCertificate")
    protected Certificate encryptionCertificate;

    public String getUri(String path) {
        final StringBuilder sb = new StringBuilder(applicationProperties.getServiceUrl());
        sb.append(path);

        logger.info("Outbound Url {} ", sb.toString());

        return sb.toString();
    }

    public HttpHeaders getHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.ACCCEPT, Constants.CONTENT_TYPE_JSON);
        headers.set(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON);
        return headers;
    }

    public <T> T decrypt(String payload, Class<T> valueType) throws EncryptionException, IOException, FdcException {
        if (payload != null) {
            String decryptPayload = FieldLevelEncryption.decryptPayload(payload, buildConfig(encryptionCertificate, decryptionKey));
            return objectMapper.readValue(decryptPayload.getBytes(), valueType);
        } else {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError("Decryption failed", "Payload", "Decryption failed due to bad replay payload."));
            throw new FdcException(ApplicationError.TOKENIZATION_FAILED.getErrorCode(), ApplicationError.TOKENIZATION_FAILED.getErrorDescription(), fieldErrors, ApplicationError.TOKENIZATION_FAILED.getCategory(), null);
        }
    }
}
