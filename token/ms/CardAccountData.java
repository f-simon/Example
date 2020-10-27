package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardAccountData implements Serializable {

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("expiryMonth")
    private String expiryMonth;

    @JsonProperty("expiryYear")
    private String expiryYear;

    @JsonProperty("securityCode")
    private String securityCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(String expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public String getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(String expiryYear) {
        this.expiryYear = expiryYear;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "CardAccountData{" +
                "accountNumber='" + accountNumber + '\'' +
                ", expiryMonth='" + expiryMonth + '\'' +
                ", expiryYear='" + expiryYear + '\'' +
                ", securityCode='" + securityCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardAccountData)) return false;
        CardAccountData that = (CardAccountData) o;
        return Objects.equals(getAccountNumber(), that.getAccountNumber()) &&
                Objects.equals(getExpiryMonth(), that.getExpiryMonth()) &&
                Objects.equals(getExpiryYear(), that.getExpiryYear()) &&
                Objects.equals(getSecurityCode(), that.getSecurityCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNumber(), getExpiryMonth(), getExpiryYear(), getSecurityCode());
    }
}
