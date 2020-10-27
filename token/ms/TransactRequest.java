package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class TransactRequest implements Serializable {

    @JsonProperty("responseHost")
    private String responseHost;

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("tokenUniqueReference")
    private String tokenUniqueReference;

    @JsonProperty("dsrpType")
    private String dsrpType;

    @JsonProperty("unpredictableNumber")
    private String unpredictableNumber;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("currencyCode")
    private String currencyCode;


    public String getResponseHost() {
        return responseHost;
    }

    public void setResponseHost(String responseHost) {
        this.responseHost = responseHost;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTokenUniqueReference() {
        return tokenUniqueReference;
    }

    public void setTokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
    }

    public String getDsrpType() {
        return dsrpType;
    }

    public void setDsrpType(String dsrpType) {
        this.dsrpType = dsrpType;
    }

    public String getUnpredictableNumber() {
        return unpredictableNumber;
    }

    public void setUnpredictableNumber(String unpredictableNumber) {
        this.unpredictableNumber = unpredictableNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "Transact Request{" +
                "responseHost='" + responseHost + '\'' +
                ", requestId='" + requestId + '\'' +
                ", tokenUniqueReference='" + tokenUniqueReference + '\'' +
                ", dsrpType='" + dsrpType + '\'' +
                ", unpredictableNumber='" + unpredictableNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactRequest)) return false;
        TransactRequest that = (TransactRequest) o;
        return  Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(tokenUniqueReference, that.tokenUniqueReference) &&
                Objects.equals(dsrpType, that.dsrpType) &&
                Objects.equals(unpredictableNumber, that.unpredictableNumber) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, requestId, tokenUniqueReference, dsrpType, unpredictableNumber, amount, currencyCode);
    }
}
