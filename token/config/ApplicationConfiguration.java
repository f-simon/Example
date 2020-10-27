package com.fdc.mtrg.network.token.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fdc.mtrg.network.token.config.interceptor.EncryptionInterceptor;
import com.fdc.mtrg.network.token.config.interceptor.OAuth1Interceptor;
import com.fdc.mtrg.network.token.util.FileUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.Proxy;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan({"com.fdc.mtrg.*"})
@EnableConfigurationProperties({ApplicationProperties.class})
public class ApplicationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Autowired
    private ApplicationProperties appProps;

    @Bean
    @Scope("prototype")
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8));
        jsonConverter.setPrettyPrint(true);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }


    public PoolingHttpClientConnectionManager serviceConnectionManager() throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException {
        final PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(appProps.getPoolingMaxTotal());
        manager.setDefaultMaxPerRoute(appProps.getPoolingDefaultMaxPerRoute());
        return manager;
    }


    public HttpClient serviceHttpClient() throws Exception {
        final PoolingHttpClientConnectionManager poolingConnManager = this.serviceConnectionManager();
        final HttpClientBuilder builder = HttpClientBuilder.create().setConnectionManager(poolingConnManager);
        return builder.build();
                //setProxy(new HttpHost(appProps.getProxyHost(), appProps.getProxyPort(), Proxy.Type.HTTP.name())).build();
    }


    @ConfigurationProperties(prefix = "com.fdc.mtrg")
    public HttpComponentsClientHttpRequestFactory serviceHttpRequestFactory() throws Exception {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(this.serviceHttpClient());
        httpComponentsClientHttpRequestFactory.setConnectTimeout(appProps.getConnectionTimeout());
        httpComponentsClientHttpRequestFactory.setReadTimeout(appProps.getReadTimeout());
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(appProps.getConnectionRequestTimeout());
        return httpComponentsClientHttpRequestFactory;
    }

    @Bean(name = "mcRestTemplate")
    public RestTemplate serviceRestTemplate() throws Exception {
        final RestTemplate serviceRestTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new EncryptionInterceptor(encryptionCertificate(), decryptionKey()));
        interceptors.add(new OAuth1Interceptor(appProps, signingKey()));
        serviceRestTemplate.setInterceptors(interceptors);
        serviceRestTemplate.setRequestFactory(serviceHttpRequestFactory());

        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        serviceRestTemplate.getMessageConverters().add(converter);
        return serviceRestTemplate;
    }

    @Bean(name = "encryptionCertificate")
    public Certificate encryptionCertificate() throws KeyStoreException, CertificateException, IOException {
        return FileUtils.loadEncryptionCertificate(appProps.getPublickKeyEncrypt());
    }

    @Bean(name = "decryptionKey")
    public PrivateKey decryptionKey() throws GeneralSecurityException, IOException {
        return FileUtils.loadDecryptionKey(appProps.getPrivateKeyDecrypt());
    }


    @Bean(name = "signingKey")
    public PrivateKey signingKey() throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, IOException {
        return FileUtils.loadSigningKey(appProps.getP12(), appProps.getKeystoreAlias(), appProps.getKeystorePassword());
    }

    @Bean(name = "decryptionKeyFromUnencryptedKey")
    public PrivateKey decryptionKeyFromUnencryptedKey() throws GeneralSecurityException, IOException {
        //PrivateKey decryptionKey = EncryptionUtils.loadDecryptionKey(appProps.getPrivateKeyFilePath());
        return null;
    }
}
