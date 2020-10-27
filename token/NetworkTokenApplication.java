package com.fdc.mtrg.network.token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableCaching
@ImportResource({"classpath:si/master-card-flow-si.xml", "classpath:si/master-card-flow-lifecycle.xml", "classpath:si/master-card-flow-gettoken.xml"})

public class NetworkTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkTokenApplication.class, args);
    }

}
