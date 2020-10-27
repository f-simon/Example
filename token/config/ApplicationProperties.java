package com.fdc.mtrg.network.token.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties("com.fdc.mtrg.security")
public class ApplicationProperties {

    /**
     * Read timeout for the RestTemplate outbound call
     */
    private int readTimeout;

    /**
     * Connect timeout for the RestTemplate outbound call
     */
    private int connectionTimeout;

    private int connectionRequestTimeout;

    private int poolingMaxTotal;

    private int poolingDefaultMaxPerRoute;

    private String keystoreAlias;

    private String keystorePassword;

    private String consumerKey;

    private String serviceUrl;

    private Resource publickKeyEncrypt;

    private Resource privateKeyDecrypt;

    private Resource p12;

    private String proxyHost;

    private int proxyPort;

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getPoolingMaxTotal() {
        return poolingMaxTotal;
    }

    public void setPoolingMaxTotal(int poolingMaxTotal) {
        this.poolingMaxTotal = poolingMaxTotal;
    }

    public int getPoolingDefaultMaxPerRoute() {
        return poolingDefaultMaxPerRoute;
    }

    public void setPoolingDefaultMaxPerRoute(int poolingDefaultMaxPerRoute) {
        this.poolingDefaultMaxPerRoute = poolingDefaultMaxPerRoute;
    }

    public String getKeystoreAlias() {
        return keystoreAlias;
    }

    public void setKeystoreAlias(String keystoreAlias) {
        this.keystoreAlias = keystoreAlias;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public Resource getPublickKeyEncrypt() {
        return publickKeyEncrypt;
    }

    public void setPublickKeyEncrypt(Resource publickKeyEncrypt) {
        this.publickKeyEncrypt = publickKeyEncrypt;
    }

    public Resource getPrivateKeyDecrypt() {
        return privateKeyDecrypt;
    }

    public void setPrivateKeyDecrypt(Resource privateKeyDecrypt) {
        this.privateKeyDecrypt = privateKeyDecrypt;
    }

    public Resource getP12() {
        return p12;
    }

    public void setP12(Resource p12) {
        this.p12 = p12;
    }
}
