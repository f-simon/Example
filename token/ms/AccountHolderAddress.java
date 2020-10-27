package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountHolderAddress implements Serializable {

    @JsonProperty("line1")
    private String line1;

    @JsonProperty("line2")
    private String line2;

    @JsonProperty("city")
    private String city;

    @JsonProperty("countrySubdivision")
    private String countrySubdivision;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("country")
    private String country;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountrySubdivision() {
        return countrySubdivision;
    }

    public void setCountrySubdivision(String countrySubdivision) {
        this.countrySubdivision = countrySubdivision;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AccountHolderAddress{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", countrySubdivision='" + countrySubdivision + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountHolderAddress)) return false;
        AccountHolderAddress that = (AccountHolderAddress) o;
        return Objects.equals(getLine1(), that.getLine1()) &&
                Objects.equals(getLine2(), that.getLine2()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getCountrySubdivision(), that.getCountrySubdivision()) &&
                Objects.equals(getPostalCode(), that.getPostalCode()) &&
                Objects.equals(getCountry(), that.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLine1(), getLine2(), getCity(), getCountrySubdivision(), getPostalCode(), getCountry());
    }
}
