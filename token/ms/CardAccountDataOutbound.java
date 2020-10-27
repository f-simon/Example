package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardAccountDataOutbound {
    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("expiryMonth")
    private String expiryMonth;

    @JsonProperty("expiryYear")
    private String expiryYear;

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

    @Override
    public String toString() {
        return "CardAccountDataOutbound{" +
                "accountNumber='" + accountNumber + '\'' +
                ", expiryMonth='" + expiryMonth + '\'' +
                ", expiryYear='" + expiryYear + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardAccountDataOutbound that = (CardAccountDataOutbound) o;
        return Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(expiryMonth, that.expiryMonth) &&
                Objects.equals(expiryYear, that.expiryYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, expiryMonth, expiryYear);
    }
}
