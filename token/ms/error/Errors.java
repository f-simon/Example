package com.fdc.mtrg.network.token.ms.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Errors {

    @JsonProperty("source")
    private String source;

    @JsonProperty("reasonCode")
    private String reasonCode;

    @JsonProperty("description")
    private String description;

    @JsonProperty("recoverable")
    private String recoverable;

    @JsonProperty("details")
    private String details;

    public String getRecoverable() {
        return recoverable;
    }

    public void setRecoverable(String recoverable) {
        this.recoverable = recoverable;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Errors{" +
                "source='" + source + '\'' +
                ", reasonCode='" + reasonCode + '\'' +
                ", description='" + description + '\'' +
                ", recoverable='" + recoverable + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Errors errors = (Errors) o;
        return Objects.equals(source, errors.source) &&
                Objects.equals(reasonCode, errors.reasonCode) &&
                Objects.equals(description, errors.description) &&
                Objects.equals(recoverable, errors.recoverable) &&
                Objects.equals(details, errors.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, reasonCode, description, recoverable, details);
    }
}
