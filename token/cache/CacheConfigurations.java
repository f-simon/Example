package com.fdc.mtrg.network.token.cache;

import com.fdc.util.cache.FdcCacheConfiguration;
import org.springframework.stereotype.Component;

@Component
public class CacheConfigurations implements FdcCacheConfiguration {

    public static final String MERCHANT_TRID_TSP_CACHE = "merchaantTRIDTSPCCache";
    private static final Long TTL = 86400L;

    @Override
    public String getCacheName() {
        return MERCHANT_TRID_TSP_CACHE;
    }

    @Override
    public Long getTimeToLive() {
        return TTL;
    }

}
