package com.fdc.mtrg.network.token.ms.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenInfo {
    @JsonProperty("tokenPanSuffix")
    private String tokenPanSuffix;

    @JsonProperty("accountPanSuffix")
    private String accountPanSuffix;

    @JsonProperty("tokenExpiry")
    private String tokenExpiry;

    @JsonProperty("accountPanExpiry")
    private String accountPanExpiry;

    @JsonProperty("dsrpCapable")
    private String dsrpCapable;

    @JsonProperty("tokenAssuranceLevel")
    private String tokenAssuranceLevel;

    @JsonProperty("productCategory")
    private String productCategory;

    public String getTokenPanSuffix() {
        return tokenPanSuffix;
    }

    public void setTokenPanSuffix(String tokenPanSuffix) {
        this.tokenPanSuffix = tokenPanSuffix;
    }

    public String getAccountPanSuffix() {
        return accountPanSuffix;
    }

    public void setAccountPanSuffix(String accountPanSuffix) {
        this.accountPanSuffix = accountPanSuffix;
    }

    public String getTokenExpiry() {
        return tokenExpiry;
    }

    public void setTokenExpiry(String tokenExpiry) {
        this.tokenExpiry = tokenExpiry;
    }

    public String getAccountPanExpiry() {
        return accountPanExpiry;
    }

    public void setAccountPanExpiry(String accountPanExpiry) {
        this.accountPanExpiry = accountPanExpiry;
    }

    public String getDsrpCapable() {
        return dsrpCapable;
    }

    public void setDsrpCapable(String dsrpCapable) {
        this.dsrpCapable = dsrpCapable;
    }

    public String getTokenAssuranceLevel() {
        return tokenAssuranceLevel;
    }

    public void setTokenAssuranceLevel(String tokenAssuranceLevel) {
        this.tokenAssuranceLevel = tokenAssuranceLevel;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "tokenPanSuffix='" + tokenPanSuffix + '\'' +
                ", accountPanSuffix='" + accountPanSuffix + '\'' +
                ", tokenExpiry='" + tokenExpiry + '\'' +
                ", accountPanExpiry='" + accountPanExpiry + '\'' +
                ", dsrpCapable='" + dsrpCapable + '\'' +
                ", tokenAssuranceLevel='" + tokenAssuranceLevel + '\'' +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenInfo tokenInfo = (TokenInfo) o;
        return Objects.equals(tokenPanSuffix, tokenInfo.tokenPanSuffix) &&
                Objects.equals(accountPanSuffix, tokenInfo.accountPanSuffix) &&
                Objects.equals(tokenExpiry, tokenInfo.tokenExpiry) &&
                Objects.equals(accountPanExpiry, tokenInfo.accountPanExpiry) &&
                Objects.equals(dsrpCapable, tokenInfo.dsrpCapable) &&
                Objects.equals(tokenAssuranceLevel, tokenInfo.tokenAssuranceLevel) &&
                Objects.equals(productCategory, tokenInfo.productCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenPanSuffix, accountPanSuffix, tokenExpiry, accountPanExpiry, dsrpCapable, tokenAssuranceLevel, productCategory);
    }
}
