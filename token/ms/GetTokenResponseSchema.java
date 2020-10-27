package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdc.mtrg.network.token.ms.error.APIError;

import java.util.Objects;

/**
 * GetTokenResponseSchema
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTokenResponseSchema extends APIError {
    @JsonProperty("responseId")
    private String responseId;

    @JsonProperty("token")
    private Token token;

    @JsonProperty("tokenDetail")
    private TokenDetailGetTokenOnly tokenDetail;

    @Override
    public String getResponseId() {
        return responseId;
    }

    @Override
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public TokenDetailGetTokenOnly getTokenDetail() {
        return tokenDetail;
    }

    public void setTokenDetail(TokenDetailGetTokenOnly tokenDetail) {
        this.tokenDetail = tokenDetail;
    }

    @Override
    public String toString() {
        return "GetTokenResponseSchema{" +
                "responseId='" + responseId + '\'' +
                ", token=" + token +
                ", tokenDetail=" + tokenDetail +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof GetTokenResponseSchema)) return false;
        if (!super.equals(o)) return false;
        GetTokenResponseSchema that = (GetTokenResponseSchema) o;
        return Objects.equals(getResponseId(), that.getResponseId()) &&
                Objects.equals(token, that.token) &&
                Objects.equals(tokenDetail, that.tokenDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResponseId(), token, tokenDetail);
    }
}

