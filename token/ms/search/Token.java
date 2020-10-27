package com.fdc.mtrg.network.token.ms.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdc.mtrg.network.token.ms.ProductConfig;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
    @JsonProperty("tokenUniqueReference")
    private String tokenUniqueReference;

    @JsonProperty("status")
    private String status;

    @JsonProperty("suspendedBy")
    private List<String> suspendedBy = null;

    @JsonProperty("statusTimestamp")
    private String statusTimestamp;

    @JsonProperty("productConfig")
    private ProductConfig productConfig;

    @JsonProperty("tokenInfo")
    private TokenInfo tokenInfo;

    public String getTokenUniqueReference() {
        return tokenUniqueReference;
    }

    public void setTokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getSuspendedBy() {
        return suspendedBy;
    }

    public void setSuspendedBy(List<String> suspendedBy) {
        this.suspendedBy = suspendedBy;
    }

    public String getStatusTimestamp() {
        return statusTimestamp;
    }

    public void setStatusTimestamp(String statusTimestamp) {
        this.statusTimestamp = statusTimestamp;
    }

    public ProductConfig getProductConfig() {
        return productConfig;
    }

    public void setProductConfig(ProductConfig productConfig) {
        this.productConfig = productConfig;
    }

    public TokenInfo getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(TokenInfo tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenUniqueReference='" + tokenUniqueReference + '\'' +
                ", status='" + status + '\'' +
                ", suspendedBy=" + suspendedBy +
                ", statusTimestamp='" + statusTimestamp + '\'' +
                ", productConfig=" + productConfig +
                ", tokenInfo=" + tokenInfo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(tokenUniqueReference, token.tokenUniqueReference) &&
                Objects.equals(status, token.status) &&
                Objects.equals(suspendedBy, token.suspendedBy) &&
                Objects.equals(statusTimestamp, token.statusTimestamp) &&
                Objects.equals(productConfig, token.productConfig) &&
                Objects.equals(tokenInfo, token.tokenInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenUniqueReference, status, suspendedBy, statusTimestamp, productConfig, tokenInfo);
    }
}
