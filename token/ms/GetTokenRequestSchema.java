package com.fdc.mtrg.network.token.ms;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetTokenRequestSchema
 */

public class GetTokenRequestSchema   {
  @JsonProperty("responseHost")
  private String responseHost;

  @JsonProperty("requestId")
  private String requestId;

  @JsonProperty("paymentAppInstanceId")
  private String paymentAppInstanceId;

  @JsonProperty("tokenUniqueReference")
  private String tokenUniqueReference;

  @JsonProperty("includeTokenDetail")
  private String includeTokenDetail;

  public GetTokenRequestSchema responseHost(String responseHost) {
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

  public GetTokenRequestSchema requestId(String requestId) {
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

  public GetTokenRequestSchema paymentAppInstanceId(String paymentAppInstanceId) {
    this.paymentAppInstanceId = paymentAppInstanceId;
    return this;
  }

  /**
   * Identifier for the specific Mobile Payment App instance, unique across a given Wallet Identifier. This value cannot be changed after digitization. This field is alphanumeric and additionally web-safe base64 characters per RFC 4648 (minus \"-\", underscore \"_\") up to a maximum length of 48, = should not be URL encoded. Conditional - not applicable for server-based tokens but required otherwise.     __Max Length:48__ 
   * @return paymentAppInstanceId
  */
  public String getPaymentAppInstanceId() {
    return paymentAppInstanceId;
  }

  public void setPaymentAppInstanceId(String paymentAppInstanceId) {
    this.paymentAppInstanceId = paymentAppInstanceId;
  }

  public GetTokenRequestSchema tokenUniqueReference(String tokenUniqueReference) {
    this.tokenUniqueReference = tokenUniqueReference;
    return this;
  }

  /**
   * The specific Token to be queried.     __Max Length:64__  
   * @return tokenUniqueReference
  */

  public String getTokenUniqueReference() {
    return tokenUniqueReference;
  }

  public void setTokenUniqueReference(String tokenUniqueReference) {
    this.tokenUniqueReference = tokenUniqueReference;
  }

  public GetTokenRequestSchema includeTokenDetail(String includeTokenDetail) {
    this.includeTokenDetail = includeTokenDetail;
    return this;
  }

  /**
   * Flag to indicate if the encrypted token should be returned.     __Max Length:5__  
   * @return includeTokenDetail
  */
  public String getIncludeTokenDetail() {
    return includeTokenDetail;
  }

  public void setIncludeTokenDetail(String includeTokenDetail) {
    this.includeTokenDetail = includeTokenDetail;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetTokenRequestSchema getTokenRequestSchema = (GetTokenRequestSchema) o;
    return Objects.equals(this.responseHost, getTokenRequestSchema.responseHost) &&
        Objects.equals(this.requestId, getTokenRequestSchema.requestId) &&
        Objects.equals(this.paymentAppInstanceId, getTokenRequestSchema.paymentAppInstanceId) &&
        Objects.equals(this.tokenUniqueReference, getTokenRequestSchema.tokenUniqueReference) &&
        Objects.equals(this.includeTokenDetail, getTokenRequestSchema.includeTokenDetail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseHost, requestId, paymentAppInstanceId, tokenUniqueReference, includeTokenDetail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetTokenRequestSchema {\n");
    
    sb.append("    responseHost: ").append(toIndentedString(responseHost)).append("\n");
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    paymentAppInstanceId: ").append(toIndentedString(paymentAppInstanceId)).append("\n");
    sb.append("    tokenUniqueReference: ").append(toIndentedString(tokenUniqueReference)).append("\n");
    sb.append("    includeTokenDetail: ").append(toIndentedString(includeTokenDetail)).append("\n");
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

