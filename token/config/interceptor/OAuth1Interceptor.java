package com.fdc.mtrg.network.token.config.interceptor;

import com.fdc.mtrg.network.token.config.ApplicationProperties;
import com.mastercard.developer.oauth.OAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

public class OAuth1Interceptor implements ClientHttpRequestInterceptor {
    private	static final Logger logger = LoggerFactory.getLogger(OAuth1Interceptor.class);

    private ApplicationProperties applicationProperties;
    private PrivateKey signingKey;

    public OAuth1Interceptor(ApplicationProperties applicationProperties, PrivateKey signingKey) {
        this.applicationProperties = applicationProperties;
        this.signingKey = signingKey;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        try {
            String payload = new String(body);
            String method = request.getMethod() == HttpMethod.GET ? "GET": "POST";
            String authHeader = OAuth.getAuthorizationHeader(request.getURI(), method, payload, StandardCharsets.UTF_8, applicationProperties.getConsumerKey(), signingKey);
            request.getHeaders().add(OAuth.AUTHORIZATION_HEADER_NAME, authHeader);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new IOException(e);
        }
        return execution.execute(request, body);
    }
}
