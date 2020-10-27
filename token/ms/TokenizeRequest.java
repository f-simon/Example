package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenizeRequest implements Serializable {

    @JsonProperty("responseHost")
    private String responseHost;

    @JsonProperty("requestId")
    private String requestId;

    @JsonProperty("tokenType")
    private String tokenType;

    @JsonProperty("tokenRequestorId")
    private String tokenRequestorId;

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("fundingAccountInfo")
    private FundingAccountInfo fundingAccountInfo;

    @JsonProperty("consumerLanguage")
    private String consumerLanguage = null;

    @JsonProperty("tokenizationAuthenticationValue")
    private String tokenizationAuthenticationValue = null;

    @JsonProperty("decisioningData")
    private DecisioningData decisioningData = null;

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

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenRequestorId() {
        return tokenRequestorId;
    }

    public void setTokenRequestorId(String tokenRequestorId) {
        this.tokenRequestorId = tokenRequestorId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public FundingAccountInfo getFundingAccountInfo() {
        return fundingAccountInfo;
    }

    public void setFundingAccountInfo(FundingAccountInfo fundingAccountInfo) {
        this.fundingAccountInfo = fundingAccountInfo;
    }

    public String getConsumerLanguage() {
        return consumerLanguage;
    }

    public void setConsumerLanguage(String consumerLanguage) {
        this.consumerLanguage = consumerLanguage;
    }

    public String getTokenizationAuthenticationValue() {
        return tokenizationAuthenticationValue;
    }

    public void setTokenizationAuthenticationValue(String tokenizationAuthenticationValue) {
        this.tokenizationAuthenticationValue = tokenizationAuthenticationValue;
    }

    public DecisioningData getDecisioningData() {
        return decisioningData;
    }

    public void setDecisioningData(DecisioningData decisioningData) {
        this.decisioningData = decisioningData;
    }

    @Override
    public String toString() {
        return "TokenizeRequest{" +
                "responseHost='" + responseHost + '\'' +
                ", requestId='" + requestId + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", tokenRequestorId='" + tokenRequestorId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", fundingAccountInfo=" + fundingAccountInfo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenizeRequest)) return false;
        TokenizeRequest that = (TokenizeRequest) o;
        return Objects.equals(responseHost, that.responseHost) &&
                Objects.equals(requestId, that.requestId) &&
                Objects.equals(tokenType, that.tokenType) &&
                Objects.equals(tokenRequestorId, that.tokenRequestorId) &&
                Objects.equals(taskId, that.taskId) &&
                Objects.equals(fundingAccountInfo, that.fundingAccountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseHost, requestId, tokenType, tokenRequestorId, taskId, fundingAccountInfo);
    }
}
