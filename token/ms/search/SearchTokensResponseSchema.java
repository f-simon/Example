package com.fdc.mtrg.network.token.ms.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdc.mtrg.network.token.ms.error.APIError;

import java.util.List;
import java.util.Objects;

public class SearchTokensResponseSchema extends APIError {
    @JsonProperty("responseHost")
    private String responseHost;

    @JsonProperty("responseId")
    private String responseId;

    @JsonProperty("tokens")
    private List<Token> tokens;

    @Override
    public String getResponseHost() {
        return responseHost;
    }

    @Override
    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    @Override
    public String getResponseId() {
        return responseId;
    }

    @Override
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "SearchTokensResponseSchema{" +
                "responseHost='" + responseHost + '\'' +
                ", responseId='" + responseId + '\'' +
                ", tokens=" + tokens +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof SearchTokensResponseSchema)) return false;
        if (!super.equals(o)) return false;
        SearchTokensResponseSchema that = (SearchTokensResponseSchema) o;
        return Objects.equals(getResponseHost(), that.getResponseHost()) &&
                Objects.equals(getResponseId(), that.getResponseId()) &&
                Objects.equals(getTokens(), that.getTokens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResponseHost(), getResponseId(), getTokens());
    }
}
