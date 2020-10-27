package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdc.mtrg.network.token.ms.error.APIError;
import com.fdc.mtrg.network.token.ms.search.TokenInfo;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenizeResponse extends APIError {
    @JsonProperty("responseHost")
    private String responseHost;

    @JsonProperty("responseId")
    private String responseId;

    @JsonProperty("decision")
    private String decision;

    @JsonProperty("tokenUniqueReference")
    private String tokenUniqueReference;

    @JsonProperty("panUniqueReference")
    private String panUniqueReference;

    @JsonProperty("authenticationMethods")
    private List<AuthenticationMethods> authenticationMethods = null;

    @JsonProperty("productConfig")
    private ProductConfig productConfig;

    @JsonProperty("tokenInfo")
    private TokenInfo tokenInfo;

    @JsonProperty("tokenDetail")
    private TokenDetail tokenDetail;

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getTokenUniqueReference() {
        return tokenUniqueReference;
    }

    public void setTokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
    }

    public String getPanUniqueReference() {
        return panUniqueReference;
    }

    public void setPanUniqueReference(String panUniqueReference) {
        this.panUniqueReference = panUniqueReference;
    }

    public List<AuthenticationMethods> getAuthenticationMethods() {
        return authenticationMethods;
    }

    public void setAuthenticationMethods(List<AuthenticationMethods> authenticationMethods) {
        this.authenticationMethods = authenticationMethods;
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

    public TokenDetail getTokenDetail() {
        return tokenDetail;
    }

    public void setTokenDetail(TokenDetail tokenDetail) {
        this.tokenDetail = tokenDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenizeResponse)) return false;
        if (!super.equals(o)) return false;
        TokenizeResponse that = (TokenizeResponse) o;
        return Objects.equals(getResponseHost(), that.getResponseHost()) &&
                Objects.equals(getResponseId(), that.getResponseId()) &&
                Objects.equals(getDecision(), that.getDecision()) &&
                Objects.equals(getTokenUniqueReference(), that.getTokenUniqueReference()) &&
                Objects.equals(getPanUniqueReference(), that.getPanUniqueReference()) &&
                Objects.equals(getAuthenticationMethods(), that.getAuthenticationMethods()) &&
                Objects.equals(getProductConfig(), that.getProductConfig()) &&
                Objects.equals(getTokenInfo(), that.getTokenInfo()) &&
                Objects.equals(getTokenDetail(), that.getTokenDetail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResponseHost(), getResponseId(), getDecision(), getTokenUniqueReference(), getPanUniqueReference(), getAuthenticationMethods(), getProductConfig(), getTokenInfo(), getTokenDetail());
    }

    @Override
    public String toString() {
        return "TokenizeResponse{" +
                "responseHost='" + responseHost + '\'' +
                ", responseId='" + responseId + '\'' +
                ", decision='" + decision + '\'' +
                ", tokenUniqueReference='" + tokenUniqueReference + '\'' +
                ", panUniqueReference='" + panUniqueReference + '\'' +
                ", authenticationMethods=" + authenticationMethods +
                ", productConfig=" + productConfig +
                ", tokenInfo=" + tokenInfo +
                ", tokenDetail=" + tokenDetail +
                '}';
    }
}


