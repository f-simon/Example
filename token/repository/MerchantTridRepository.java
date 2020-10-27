package com.fdc.mtrg.network.token.repository;

import com.fdc.mtrg.network.token.entity.MerchantTridMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchantTridRepository extends JpaRepository<MerchantTridMapping, String> {

    List<MerchantTridMapping> findByMerchantIdAndTsp(String merchantId, String Tsp);
}
