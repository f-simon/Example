package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountHolderData implements Serializable {

    @JsonProperty("accountHolderName")
    private String accountHolderName;

    @JsonProperty("consumerIdentifier")
    private String consumerIdentifier;

    @JsonProperty("accountHolderEmailAddress")
    private String accountHolderEmailAddress;

    @JsonProperty("accountHolderAddress")
    private AccountHolderAddress accountHolderAddress;

    @JsonProperty("accountHolderMobilePhoneNumber")
    private AccountHolderMobilePhoneNumber accountHolderMobilePhoneNumber;

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getConsumerIdentifier() {
        return consumerIdentifier;
    }

    public void setConsumerIdentifier(String consumerIdentifier) {
        this.consumerIdentifier = consumerIdentifier;
    }

    public String getAccountHolderEmailAddress() {
        return accountHolderEmailAddress;
    }

    public void setAccountHolderEmailAddress(String accountHolderEmailAddress) {
        this.accountHolderEmailAddress = accountHolderEmailAddress;
    }

    public AccountHolderAddress getAccountHolderAddress() {
        return accountHolderAddress;
    }

    public void setAccountHolderAddress(AccountHolderAddress accountHolderAddress) {
        this.accountHolderAddress = accountHolderAddress;
    }

    public AccountHolderMobilePhoneNumber getAccountHolderMobilePhoneNumber() {
        return accountHolderMobilePhoneNumber;
    }

    public void setAccountHolderMobilePhoneNumber(AccountHolderMobilePhoneNumber accountHolderMobilePhoneNumber) {
        this.accountHolderMobilePhoneNumber = accountHolderMobilePhoneNumber;
    }

    @Override
    public String toString() {
        return "AccountHolderData{" +
                "accountHolderName='" + accountHolderName + '\'' +
                ", consumerIdentifier='" + consumerIdentifier + '\'' +
                ", accountHolderEmailAddress='" + accountHolderEmailAddress + '\'' +
                ", accountHolderAddress=" + accountHolderAddress +
                ", accountHolderMobilePhoneNumber=" + accountHolderMobilePhoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountHolderData)) return false;
        AccountHolderData that = (AccountHolderData) o;
        return Objects.equals(getAccountHolderName(), that.getAccountHolderName()) &&
                Objects.equals(getConsumerIdentifier(), that.getConsumerIdentifier()) &&
                Objects.equals(getAccountHolderEmailAddress(), that.getAccountHolderEmailAddress()) &&
                Objects.equals(getAccountHolderAddress(), that.getAccountHolderAddress()) &&
                Objects.equals(getAccountHolderMobilePhoneNumber(), that.getAccountHolderMobilePhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountHolderName(), getConsumerIdentifier(), getAccountHolderEmailAddress(), getAccountHolderAddress(), getAccountHolderMobilePhoneNumber());
    }
}

