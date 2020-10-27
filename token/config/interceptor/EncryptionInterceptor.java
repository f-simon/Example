package com.fdc.mtrg.network.token.config.interceptor;

import com.mastercard.developer.encryption.FieldLevelEncryption;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import static com.fdc.mtrg.network.token.util.FileUtils.buildConfig;

public class EncryptionInterceptor implements ClientHttpRequestInterceptor {
    private	static final Logger logger = LoggerFactory.getLogger(EncryptionInterceptor.class);

    private Certificate encryptionCertificate;
    private PrivateKey decryptionKey;

    public EncryptionInterceptor(Certificate encryptionCertificate, PrivateKey decryptionKey) {
        this.encryptionCertificate = encryptionCertificate;
        this.decryptionKey = decryptionKey;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        try {
            String payload = new String(body);
            if (StringUtils.isNotBlank(payload)) {
               String encryptedPayload = FieldLevelEncryption.encryptPayload(payload, buildConfig(encryptionCertificate, decryptionKey));
               body = encryptedPayload.getBytes();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IOException("Failed to ecrypt the payload!", e);
        }
        return execution.execute(request, body);
    }
}