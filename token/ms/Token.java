package com.fdc.mtrg.network.token.ms;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fdc.mtrg.network.token.ms.search.TokenInfo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;


/**
 * Token
 */
public class Token   {
  @JsonProperty("tokenUniqueReference")
  private String tokenUniqueReference;

  @JsonProperty("status")
  private String status;

  @JsonProperty("suspendedBy")
  @Valid
  private List<String> suspendedBy = null;

  @JsonProperty("statusTimestamp")
  private String statusTimestamp;

  @JsonProperty("productConfig")
  private ProductConfig productConfig;

  @JsonProperty("tokenInfo")
  private TokenInfo tokenInfo;

  public Token tokenUniqueReference(String tokenUniqueReference) {
    this.tokenUniqueReference = tokenUniqueReference;
    return this;
  }

  /**
   * The unique reference allocated to the Token which is always present even if an error occurs. <br>      __Max Length:64__ 
   * @return tokenUniqueReference
  */
  public String getTokenUniqueReference() {
    return tokenUniqueReference;
  }

  public void setTokenUniqueReference(String tokenUniqueReference) {
    this.tokenUniqueReference = tokenUniqueReference;
  }

  public Token status(String status) {
    this.status = status;
    return this;
  }

  /**
   * The current status of Token. Must be either:    * 'INACTIVE' (Token has not yet been activated)  * 'ACTIVE' (Token is active and ready to transact)  * 'SUSPENDED' (Token is suspended and unable to transact)  * 'DEACTIVATED' (Token has been permanently deactivated).<br>      __Max Length:32__ 
   * @return status
  */
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Token suspendedBy(List<String> suspendedBy) {
    this.suspendedBy = suspendedBy;
    return this;
  }

  public Token addSuspendedByItem(String suspendedByItem) {
    if (this.suspendedBy == null) {
      this.suspendedBy = new ArrayList<>();
    }
    this.suspendedBy.add(suspendedByItem);
    return this;
  }

  /**
   * (CONDITIONAL only supplied if status is SUSPENDED) Who or what caused the Token to be suspended One or more values of:     * ISSUER - Suspended by the Issuer.    * TOKEN_REQUESTOR - Suspended by the Token Requestor     * MOBILE_PIN_LOCKED - Suspended due to the Mobile PIN being locked    * CARDHOLDER - Suspended by the Cardholder <br>          __Max Length__: N/A     
   * @return suspendedBy
  */
  public List<String> getSuspendedBy() {
    return suspendedBy;
  }

  public void setSuspendedBy(List<String> suspendedBy) {
    this.suspendedBy = suspendedBy;
  }

  public Token statusTimestamp(String statusTimestamp) {
    this.statusTimestamp = statusTimestamp;
    return this;
  }

  /**
   * The date and time the token status was last updated. Expressed in ISO 8601 extended format as one of the following:     * YYYY-MM-DDThh:mm:ss[.sss]Z    * YYYY-MM-DDThh:mm:ss[.sss]�hh:mm    * Where [.sss] is optional and can be 1 to 3 digits. <br>  __Max Length:29__   
   * @return statusTimestamp
  */
  public String getStatusTimestamp() {
    return statusTimestamp;
  }

  public void setStatusTimestamp(String statusTimestamp) {
    this.statusTimestamp = statusTimestamp;
  }

  public Token productConfig(ProductConfig productConfig) {
    this.productConfig = productConfig;
    return this;
  }

  /**
   * Get productConfig
   * @return productConfig
  */


  @Valid
  public ProductConfig getProductConfig() {
    return productConfig;
  }

  public void setProductConfig(ProductConfig productConfig) {
    this.productConfig = productConfig;
  }

  public Token tokenInfo(TokenInfo tokenInfo) {
    this.tokenInfo = tokenInfo;
    return this;
  }

  /**
   * Get tokenInfo
   * @return tokenInfo
  */

  @Valid
  public TokenInfo getTokenInfo() {
    return tokenInfo;
  }

  public void setTokenInfo(TokenInfo tokenInfo) {
    this.tokenInfo = tokenInfo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Token token = (Token) o;
    return Objects.equals(this.tokenUniqueReference, token.tokenUniqueReference) &&
        Objects.equals(this.status, token.status) &&
        Objects.equals(this.suspendedBy, token.suspendedBy) &&
        Objects.equals(this.statusTimestamp, token.statusTimestamp) &&
        Objects.equals(this.productConfig, token.productConfig) &&
        Objects.equals(this.tokenInfo, token.tokenInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokenUniqueReference, status, suspendedBy, statusTimestamp, productConfig, tokenInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Token {\n");
    
    sb.append("    tokenUniqueReference: ").append(toIndentedString(tokenUniqueReference)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    suspendedBy: ").append(toIndentedString(suspendedBy)).append("\n");
    sb.append("    statusTimestamp: ").append(toIndentedString(statusTimestamp)).append("\n");
    sb.append("    productConfig: ").append(toIndentedString(productConfig)).append("\n");
    sb.append("    tokenInfo: ").append(toIndentedString(tokenInfo)).append("\n");
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

