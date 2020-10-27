package com.fdc.mtrg.network.token.ms;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SuspendRequestSchema
 */

public class SuspendRequestSchema   {
  @JsonProperty("responseHost")
  private String responseHost;

  @JsonProperty("requestId")
  private String requestId;

  @JsonProperty("paymentAppInstanceId")
  private String paymentAppInstanceId;

  @JsonProperty("tokenUniqueReferences")
  @Valid
  private List<String> tokenUniqueReferences = new ArrayList<>();

  @JsonProperty("causedBy")
  private String causedBy;

  @JsonProperty("reason")
  private String reason;

  @JsonProperty("reasonCode")
  private String reasonCode;

  public SuspendRequestSchema responseHost(String responseHost) {
    this.responseHost = responseHost;
    return this;
  }

  /**
   * The host that originated the request. Future calls in the same conversation may be routed to this host. 
   * @return responseHost
  */
  public String getResponseHost() {
    return responseHost;
  }

  public void setResponseHost(String responseHost) {
    this.responseHost = responseHost;
  }

  public SuspendRequestSchema requestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  /**
   * Unique identifier for the request. 
   * @return requestId
  */

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public SuspendRequestSchema paymentAppInstanceId(String paymentAppInstanceId) {
    this.paymentAppInstanceId = paymentAppInstanceId;
    return this;
  }

  /**
   * Identifier for the specific Mobile Payment App instance, unique across a given Wallet Identifier. This value cannot be changed after digitization. This field is alphanumeric and additionally web-safe base64 characters per RFC 4648 (minus \"-\", underscore \"_\") up to a maximum length of 48, = should not be URL encoded. Conditional - not applicable for server based tokens but required otherwise.     __Max Length:48__ 
   * @return paymentAppInstanceId
  */
  public String getPaymentAppInstanceId() {
    return paymentAppInstanceId;
  }

  public void setPaymentAppInstanceId(String paymentAppInstanceId) {
    this.paymentAppInstanceId = paymentAppInstanceId;
  }

  public SuspendRequestSchema tokenUniqueReferences(List<String> tokenUniqueReferences) {
    this.tokenUniqueReferences = tokenUniqueReferences;
    return this;
  }

  public SuspendRequestSchema addTokenUniqueReferencesItem(String tokenUniqueReferencesItem) {
    this.tokenUniqueReferences.add(tokenUniqueReferencesItem);
    return this;
  }

  /**
   * The specific Token to be suspended. Array of more or more valid references as assigned by MDES  
   * @return tokenUniqueReferences
  */
  public List<String> getTokenUniqueReferences() {
    return tokenUniqueReferences;
  }

  public void setTokenUniqueReferences(List<String> tokenUniqueReferences) {
    this.tokenUniqueReferences = tokenUniqueReferences;
  }

  public SuspendRequestSchema causedBy(String causedBy) {
    this.causedBy = causedBy;
    return this;
  }

  /**
   * Who or what caused the Token to be suspended. Must be either the 'CARDHOLDER' (operation requested by the Cardholder) or 'TOKEN_REQUESTOR' (operation requested by the token requestor).     __Max Length:64__ 
   * @return causedBy
  */
  public String getCausedBy() {
    return causedBy;
  }

  public void setCausedBy(String causedBy) {
    this.causedBy = causedBy;
  }

  public SuspendRequestSchema reason(String reason) {
    this.reason = reason;
    return this;
  }

  /**
   * Free form reason why the Tokens are being suspended.     __Max Length:256__ 
   * @return reason
  */
  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public SuspendRequestSchema reasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
    return this;
  }

  /**
   * The reason for the action to be suspended. Must be one of 'SUSPECTED_FRAUD' (suspected fraudulent token transactions), 'OTHER' (Other - default used if value not provided).     __Max Length:64__ 
   * @return reasonCode
  */
  public String getReasonCode() {
    return reasonCode;
  }

  public void setReasonCode(String reasonCode) {
    this.reasonCode = reasonCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SuspendRequestSchema suspendRequestSchema = (SuspendRequestSchema) o;
    return Objects.equals(this.responseHost, suspendRequestSchema.responseHost) &&
        Objects.equals(this.requestId, suspendRequestSchema.requestId) &&
        Objects.equals(this.paymentAppInstanceId, suspendRequestSchema.paymentAppInstanceId) &&
        Objects.equals(this.tokenUniqueReferences, suspendRequestSchema.tokenUniqueReferences) &&
        Objects.equals(this.causedBy, suspendRequestSchema.causedBy) &&
        Objects.equals(this.reason, suspendRequestSchema.reason) &&
        Objects.equals(this.reasonCode, suspendRequestSchema.reasonCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseHost, requestId, paymentAppInstanceId, tokenUniqueReferences, causedBy, reason, reasonCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SuspendRequestSchema {\n");
    
    sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("\n");
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    paymentAppInstanceId: ").append(toIndentedString(paymentAppInstanceId)).append("\n");
    sb.append("    tokenUniqueReferences: ").append(toIndentedString(tokenUniqueReferences)).append("\n");
    sb.append("    causedBy: ").append(toIndentedString(causedBy)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    reasonCode: ").append(toIndentedString(reasonCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

