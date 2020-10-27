package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountHolderMobilePhoneNumber implements Serializable {

    @JsonProperty("countryDialInCode")
    private String countryDialInCode;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    public String getCountryDialInCode() {
        return countryDialInCode;
    }

    public void setCountryDialInCode(String countryDialInCode) {
        this.countryDialInCode = countryDialInCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "AccountHolderMobilePhoneNumber{" +
                "countryDialInCode='" + countryDialInCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountHolderMobilePhoneNumber)) return false;
        AccountHolderMobilePhoneNumber that = (AccountHolderMobilePhoneNumber) o;
        return Objects.equals(getCountryDialInCode(), that.getCountryDialInCode()) &&
                Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryDialInCode(), getPhoneNumber());
    }
}
