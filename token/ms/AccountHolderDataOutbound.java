package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountHolderDataOutbound {
    @JsonProperty("accountHolderName")
    private String accountHolderName;

    @JsonProperty("accountHolderAddress")
    private BillingAddress accountHolderAddress;

    @JsonProperty("accountHolderEmailAddress")
    private String accountHolderEmailAddress;

    @JsonProperty("accountHolderMobilePhoneNumber")
    private PhoneNumber accountHolderMobilePhoneNumber;

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public BillingAddress getAccountHolderAddress() {
        return accountHolderAddress;
    }

    public void setAccountHolderAddress(BillingAddress accountHolderAddress) {
        this.accountHolderAddress = accountHolderAddress;
    }

    public String getAccountHolderEmailAddress() {
        return accountHolderEmailAddress;
    }

    public void setAccountHolderEmailAddress(String accountHolderEmailAddress) {
        this.accountHolderEmailAddress = accountHolderEmailAddress;
    }

    public PhoneNumber getAccountHolderMobilePhoneNumber() {
        return accountHolderMobilePhoneNumber;
    }

    public void setAccountHolderMobilePhoneNumber(PhoneNumber accountHolderMobilePhoneNumber) {
        this.accountHolderMobilePhoneNumber = accountHolderMobilePhoneNumber;
    }

    @Override
    public String toString() {
        return "AccountHolderDataOutbound{" +
                "accountHolderName='" + accountHolderName + '\'' +
                ", accountHolderAddress=" + accountHolderAddress +
                ", accountHolderEmailAddress='" + accountHolderEmailAddress + '\'' +
                ", accountHolderMobilePhoneNumber=" + accountHolderMobilePhoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountHolderDataOutbound that = (AccountHolderDataOutbound) o;
        return Objects.equals(accountHolderName, that.accountHolderName) &&
                Objects.equals(accountHolderEmailAddress, that.accountHolderEmailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountHolderName, accountHolderEmailAddress);
    }
}
