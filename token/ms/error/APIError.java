package com.fdc.mtrg.network.token.ms.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class APIError {

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("errorDescription")
    private String errorDescription;

    @JsonProperty("responseHost")
    private String responseHost;

    @JsonProperty("responseId")
    private String responseId;

    @JsonProperty("errors")
    private List<Errors> errors;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getResponseHost() {
        return responseHost;
    }

    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public List<Errors> getErrors() {
        return errors;
    }

    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof APIError)) return false;
        APIError apiError = (APIError) o;
        return Objects.equals(getErrorCode(), apiError.getErrorCode()) &&
                Objects.equals(getErrorDescription(), apiError.getErrorDescription()) &&
                Objects.equals(getResponseHost(), apiError.getResponseHost()) &&
                Objects.equals(getResponseId(), apiError.getResponseId()) &&
                Objects.equals(getErrors(), apiError.getErrors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getErrorCode(), getErrorDescription(), getResponseHost(), getResponseId(), getErrors());
    }

    @Override
    public String toString() {
        return "APIError{" +
                "errorCode='" + errorCode + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", responseHost='" + responseHost + '\'' +
                ", responseId='" + responseId + '\'' +
                ", errors=" + errors +
                '}';
    }
}
