package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumber {
    @JsonProperty("countryDialInCode")
    private BigDecimal countryDialInCode;

    @JsonProperty("phoneNumber")
    private BigDecimal phoneNumber;

    public BigDecimal getCountryDialInCode() {
        return countryDialInCode;
    }

    public void setCountryDialInCode(BigDecimal countryDialInCode) {
        this.countryDialInCode = countryDialInCode;
    }

    public BigDecimal getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(BigDecimal phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "countryDialInCode=" + countryDialInCode +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(countryDialInCode, that.countryDialInCode) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryDialInCode, phoneNumber);
    }
}
