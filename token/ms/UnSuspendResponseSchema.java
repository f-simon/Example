package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdc.mtrg.network.token.ms.error.APIError;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * UnSuspendResponseSchema
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnSuspendResponseSchema extends APIError {
    @JsonProperty("responseHost")
    private String responseHost;

    @JsonProperty("responseId")
    private String responseId;

    @JsonProperty("tokens")
    @Valid
    private List<TokenForLCM> tokens = null;

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

    public List<TokenForLCM> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokenForLCM> tokens) {
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "UnSuspendResponseSchema{" +
                "responseHost='" + responseHost + '\'' +
                ", responseId='" + responseId + '\'' +
                ", tokens=" + tokens +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnSuspendResponseSchema)) return false;
        if (!super.equals(o)) return false;
        UnSuspendResponseSchema that = (UnSuspendResponseSchema) o;
        return Objects.equals(getResponseHost(), that.getResponseHost()) &&
                Objects.equals(getResponseId(), that.getResponseId()) &&
                Objects.equals(getTokens(), that.getTokens());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResponseHost(), getResponseId(), getTokens());
    }
}

