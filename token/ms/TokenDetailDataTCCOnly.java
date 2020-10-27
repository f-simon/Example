package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenDetailDataTCCOnly {
    @JsonProperty("accountHolderData")
    private AccountHolderDataOutbound accountHolderData;

    @JsonProperty("paymentAccountReference")
    private String paymentAccountReference;

    @JsonProperty("cardAccountData")
    private CardAccountDataOutbound cardAccountData;

    public AccountHolderDataOutbound getAccountHolderData() {
        return accountHolderData;
    }

    public void setAccountHolderData(AccountHolderDataOutbound accountHolderData) {
        this.accountHolderData = accountHolderData;
    }

    public String getPaymentAccountReference() {
        return paymentAccountReference;
    }

    public void setPaymentAccountReference(String paymentAccountReference) {
        this.paymentAccountReference = paymentAccountReference;
    }

    public CardAccountDataOutbound getCardAccountData() {
        return cardAccountData;
    }

    public void setCardAccountData(CardAccountDataOutbound cardAccountData) {
        this.cardAccountData = cardAccountData;
    }

    @Override
    public String toString() {
        return "TokenDetailDataTCCOnly{" +
                "paymentAccountReference='" + paymentAccountReference + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenDetailDataTCCOnly that = (TokenDetailDataTCCOnly) o;
        return Objects.equals(paymentAccountReference, that.paymentAccountReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentAccountReference);
    }
}
