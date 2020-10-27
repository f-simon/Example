package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EncryptedData implements Serializable {

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("applicationExpiryDate")
    private String applicationExpiryDate;

    @JsonProperty("panSequenceNumber")
    private String panSequenceNumber;

    @JsonProperty("track2Equivalent")
    private String track2Equivalent;

    @JsonProperty("de48se43Data")
    private String de48se43Data;

    @JsonProperty("de55Data")
    private String de55Data;

    @JsonProperty("cardAccountData")
    private CardAccountData cardAccountData;

    @JsonProperty("accountHolderData")
    private AccountHolderData accountHolderData;

    @JsonProperty("source")
    private String source;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getApplicationExpiryDate() {
        return applicationExpiryDate;
    }

    public void setApplicationExpiryDate(String applicationExpiryDate) {
        this.applicationExpiryDate = applicationExpiryDate;
    }

    public String getPanSequenceNumber() {
        return panSequenceNumber;
    }

    public void setPanSequenceNumber(String panSequenceNumber) {
        this.panSequenceNumber = panSequenceNumber;
    }

    public String getTrack2Equivalent() {
        return track2Equivalent;
    }

    public void setTrack2Equivalent(String track2Equivalent) {
        this.track2Equivalent = track2Equivalent;
    }

    public String getDe48se43Data() {
        return de48se43Data;
    }

    public void setDe48se43Data(String de48se43Data) {
        this.de48se43Data = de48se43Data;
    }

    public String getDe55Data() {
        return de55Data;
    }

    public void setDe55Data(String de55Data) {
        this.de55Data = de55Data;
    }

    public CardAccountData getCardAccountData() {
        return cardAccountData;
    }

    public void setCardAccountData(CardAccountData cardAccountData) {
        this.cardAccountData = cardAccountData;
    }

    public AccountHolderData getAccountHolderData() {
        return accountHolderData;
    }

    public void setAccountHolderData(AccountHolderData accountHolderData) {
        this.accountHolderData = accountHolderData;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "EncryptedData{" +
                "accountNumber=" + accountNumber +
                "applicationExpiryDate=" + applicationExpiryDate +
                "panSequenceNumber=" + panSequenceNumber +
                "track2Equivalent=" + track2Equivalent +
                "de48se43Data=" + de48se43Data +
                "de55Data=" + de55Data +
                "cardAccountData=" + cardAccountData +
                ", accountHolderData=" + accountHolderData +
                ", source='" + source + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof EncryptedData)) return false;
        EncryptedData that = (EncryptedData) o;
        return Objects.equals(getCardAccountData(), that.getCardAccountData()) &&
                Objects.equals(getAccountNumber(), that.getAccountNumber()) &&
                Objects.equals(getApplicationExpiryDate(), that.applicationExpiryDate) &&
                Objects.equals(getPanSequenceNumber(), that.panSequenceNumber) &&
                Objects.equals(getTrack2Equivalent(), that.track2Equivalent) &&
                Objects.equals(getDe48se43Data(), that.de48se43Data) &&
                Objects.equals(getDe55Data(), that.de55Data) &&
                Objects.equals(getAccountHolderData(), that.getAccountHolderData()) &&
                Objects.equals(getSource(), that.getSource());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardAccountData(), getAccountHolderData(), getSource(),
                getAccountNumber(), getApplicationExpiryDate(), getPanSequenceNumber(),
                getTrack2Equivalent(), getDe48se43Data(), getDe55Data());
    }
}
