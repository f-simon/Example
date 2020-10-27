package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdc.mtrg.network.token.ms.error.APIError;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactResponse extends APIError {

    @JsonProperty("responseId")
    private String responseId;

    @JsonProperty("responseHost")
    private String responseHost;

    @JsonProperty("encryptedPayload")
    private EncryptedPayload encryptedPayload;

    @JsonProperty("transactError")
    private APIError TransactError;

    @Override
    public String getResponseId() {
        return responseId;
    }

    @Override
    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    @Override
    public String getResponseHost() {
        return responseHost;
    }

    @Override
    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    public EncryptedPayload getEncryptedPayload() {
        return encryptedPayload;
    }

    public void setEncryptedPayload(EncryptedPayload encryptedPayload) {
        this.encryptedPayload = encryptedPayload;
    }

    public APIError getTransactError() {
        return TransactError;
    }

    public void setTransactError(APIError transactError) {
        TransactError = transactError;
    }

    @Override
    public String toString() {
        return "TransactResponse{" +
                "responseId='" + responseId + '\'' +
                ", responseHost='" + responseHost + '\'' +
                ", encryptedPayload=" + encryptedPayload +
                ", TransactError=" + TransactError +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof TransactResponse)) return false;
        if (!super.equals(o)) return false;
        TransactResponse that = (TransactResponse) o;
        return Objects.equals(getResponseId(), that.getResponseId()) &&
                Objects.equals(getResponseHost(), that.getResponseHost()) &&
                Objects.equals(getEncryptedPayload(), that.getEncryptedPayload()) &&
                Objects.equals(getTransactError(), that.getTransactError());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getResponseId(), getResponseHost(), getEncryptedPayload(), getTransactError());
    }
}
