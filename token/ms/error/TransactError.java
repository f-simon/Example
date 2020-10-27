package com.fdc.mtrg.network.token.ms.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactError {

    @JsonProperty("source")
    private String source;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("description")
    private String description;

    @JsonProperty("reasonCode")
    private String reasonCode;

    @JsonProperty("errorDescription")
    private String errorDescription;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactError)) return false;
        TransactError that = (TransactError) o;
        return Objects.equals(getSource(), that.getSource()) &&
                Objects.equals(getErrorCode(), that.getErrorCode()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getReasonCode(), that.getReasonCode()) &&
                Objects.equals(getErrorDescription(), that.getErrorDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSource(), getErrorCode(), getDescription(), getReasonCode(), getErrorDescription());
    }

    @Override
    public String toString() {
        return "TransactError{" +
                "source='" + source + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", description='" + description + '\'' +
                ", reasonCode='" + reasonCode + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                '}';
    }
}
