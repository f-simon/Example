package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FundingAccountInfo implements Serializable {

    @JsonProperty("encryptedPayload")
    private EncryptedPayload encryptedPayload;

    public EncryptedPayload getEncryptedPayload() {
        return encryptedPayload;
    }

    public void setEncryptedPayload(EncryptedPayload encryptedPayload) {
        this.encryptedPayload = encryptedPayload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FundingAccountInfo)) return false;
        FundingAccountInfo that = (FundingAccountInfo) o;
        return Objects.equals(getEncryptedPayload(), that.getEncryptedPayload());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEncryptedPayload());
    }

    @Override
    public String toString() {
        return "FundingAccountInfo{" +
                "encryptedPayload=" + encryptedPayload +
                '}';
    }
}
