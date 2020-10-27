package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DecisioningData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-04T18:27:22.925Z")

public class DecisioningData {
  @JsonProperty("recommendation")
  private String recommendation = null;

  @JsonProperty("recommendationAlgorithmVersion")
  private String recommendationAlgorithmVersion = null;

  @JsonProperty("deviceScore")
  private String deviceScore = null;

  @JsonProperty("accountScore")
  private String accountScore = null;

  @JsonProperty("recommendationReasons")
  @Valid
  private List<String> recommendationReasons = null;

  @JsonProperty("deviceCurrentLocation")
  private String deviceCurrentLocation = null;

  @JsonProperty("deviceIpAddress")
  private String deviceIpAddress = null;

  @JsonProperty("mobileNumberSuffix")
  private String mobileNumberSuffix = null;

  @JsonProperty("accountIdHash")
  private String accountIdHash = null;

  public DecisioningData recommendation(String recommendation) {
    this.recommendation = recommendation;
    return this;
  }

  /**
   * __(OPTIONAL)__ <br> Digitization decision recommended by the Token Requestor. Must be either APPROVED (Recommend a decision of Approved), DECLINED (Recommend a decision of Decline). <br>   __Max Length:64__ 
   * @return recommendation
  **/
  @ApiModelProperty(example = "APPROVE", value = "__(OPTIONAL)__ <br> Digitization decision recommended by the Token Requestor. Must be either APPROVED (Recommend a decision of Approved), DECLINED (Recommend a decision of Decline). <br>   __Max Length:64__ ")


  public String getRecommendation() {
    return recommendation;
  }

  public void setRecommendation(String recommendation) {
    this.recommendation = recommendation;
  }

  public DecisioningData recommendationAlgorithmVersion(String recommendationAlgorithmVersion) {
    this.recommendationAlgorithmVersion = recommendationAlgorithmVersion;
    return this;
  }

  /**
   * __(OPTIONAL)__ <br> Version of the algorithm used by the Token Requestor to determine its recommendation. Must be a value of \"01\". Other values may be supported in the future.<br>     __Max Length:16__ 
   * @return recommendationAlgorithmVersion
  **/
  @ApiModelProperty(example = "01", value = "__(OPTIONAL)__ <br> Version of the algorithm used by the Token Requestor to determine its recommendation. Must be a value of \"01\". Other values may be supported in the future.<br>     __Max Length:16__ ")


  public String getRecommendationAlgorithmVersion() {
    return recommendationAlgorithmVersion;
  }

  public void setRecommendationAlgorithmVersion(String recommendationAlgorithmVersion) {
    this.recommendationAlgorithmVersion = recommendationAlgorithmVersion;
  }

  public DecisioningData deviceScore(String deviceScore) {
    this.deviceScore = deviceScore;
    return this;
  }

  /**
   * __(OPTIONAL)__ <br> Score assigned by the Token Requestor for the target device being provisioned. Must be a value from 1 to 5. 
   * @return deviceScore
  **/
  @ApiModelProperty(example = "1", value = "__(OPTIONAL)__ <br> Score assigned by the Token Requestor for the target device being provisioned. Must be a value from 1 to 5. ")


  public String getDeviceScore() {
    return deviceScore;
  }

  public void setDeviceScore(String deviceScore) {
    this.deviceScore = deviceScore;
  }

  public DecisioningData accountScore(String accountScore) {
    this.accountScore = accountScore;
    return this;
  }

  /**
   * __(OPTIONAL)__ <br> Score assigned by the Token Requestor for the consumer account or relationship. Must be a value from 1 to 5. 
   * @return accountScore
  **/
  @ApiModelProperty(example = "1", value = "__(OPTIONAL)__ <br> Score assigned by the Token Requestor for the consumer account or relationship. Must be a value from 1 to 5. ")


  public String getAccountScore() {
    return accountScore;
  }

  public void setAccountScore(String accountScore) {
    this.accountScore = accountScore;
  }

  public DecisioningData recommendationReasons(List<String> recommendationReasons) {
    this.recommendationReasons = recommendationReasons;
    return this;
  }

  public DecisioningData addRecommendationReasonsItem(String recommendationReasonsItem) {
    if (this.recommendationReasons == null) {
      this.recommendationReasons = new ArrayList<String>();
    }
    this.recommendationReasons.add(recommendationReasonsItem);
    return this;
  }

  /**
   * __(OPTIONAL)__ <br> Code indicating the reasons the Token Requestor is suggesting the digitization decision.  See table here - https://developer.mastercard.com/page/mdes-digitization-recommendation-reason-codes 
   * @return recommendationReasons
  **/
  @ApiModelProperty(value = "__(OPTIONAL)__ <br> Code indicating the reasons the Token Requestor is suggesting the digitization decision.  See table here - https://developer.mastercard.com/page/mdes-digitization-recommendation-reason-codes ")


  public List<String> getRecommendationReasons() {
    return recommendationReasons;
  }

  public void setRecommendationReasons(List<String> recommendationReasons) {
    this.recommendationReasons = recommendationReasons;
  }

  public DecisioningData deviceCurrentLocation(String deviceCurrentLocation) {
    this.deviceCurrentLocation = deviceCurrentLocation;
    return this;
  }

  /**
   * __(OPTIONAL)__ <br> Latitude and longitude in the format \"(sign) latitude, (sign) longitude\" with a precision of 2 decimal places.  Ex - \"38.63, -90.25\"  Latitude is between -90 and 90.  Longitude between -180 and 180. Relates to the target device being provisioned. If there is no target device, then this should be the current consumer location, if available. <br>    __Max Length:14__ 
   * @return deviceCurrentLocation
  **/
  @ApiModelProperty(example = "38.63, -90.25", value = "__(OPTIONAL)__ <br> Latitude and longitude in the format \"(sign) latitude, (sign) longitude\" with a precision of 2 decimal places.  Ex - \"38.63, -90.25\"  Latitude is between -90 and 90.  Longitude between -180 and 180. Relates to the target device being provisioned. If there is no target device, then this should be the current consumer location, if available. <br>    __Max Length:14__ ")


  public String getDeviceCurrentLocation() {
    return deviceCurrentLocation;
  }

  public void setDeviceCurrentLocation(String deviceCurrentLocation) {
    this.deviceCurrentLocation = deviceCurrentLocation;
  }

  public DecisioningData deviceIpAddress(String deviceIpAddress) {
    this.deviceIpAddress = deviceIpAddress;
    return this;
  }

  /**
   * __(OPTIONAL)__ <br> The IP address of the device through which the device reaches the internet. This may be a temporary or permanent IP address assigned to a home router, or the IP address of a gateway through which the device connects to a network. IPv4 address format of 4 octets separated by \".\" Ex - 127.0.0.1 Relates to the target device being provisioned. If there is no target device, then this should be the current consumer IP address, if available.<br>     __Max Length:15__ 
   * @return deviceIpAddress
  **/
  @ApiModelProperty(example = "127.0.0.1", value = "__(OPTIONAL)__ <br> The IP address of the device through which the device reaches the internet. This may be a temporary or permanent IP address assigned to a home router, or the IP address of a gateway through which the device connects to a network. IPv4 address format of 4 octets separated by \".\" Ex - 127.0.0.1 Relates to the target device being provisioned. If there is no target device, then this should be the current consumer IP address, if available.<br>     __Max Length:15__ ")


  public String getDeviceIpAddress() {
    return deviceIpAddress;
  }

  public void setDeviceIpAddress(String deviceIpAddress) {
    this.deviceIpAddress = deviceIpAddress;
  }

  public DecisioningData mobileNumberSuffix(String mobileNumberSuffix) {
    this.mobileNumberSuffix = mobileNumberSuffix;
    return this;
  }

  /**
   * __(OPTIONAL)__<br> The last few digits (typically four) of the consumer's mobile phone number as available on file or on the consumer's current device, which may or may not be the mobile number of the target device being provisioned.<br>     __Max Length:32__ 
   * @return mobileNumberSuffix
  **/
  @ApiModelProperty(example = "3456", value = "__(OPTIONAL)__<br> The last few digits (typically four) of the consumer's mobile phone number as available on file or on the consumer's current device, which may or may not be the mobile number of the target device being provisioned.<br>     __Max Length:32__ ")


  public String getMobileNumberSuffix() {
    return mobileNumberSuffix;
  }

  public void setMobileNumberSuffix(String mobileNumberSuffix) {
    this.mobileNumberSuffix = mobileNumberSuffix;
  }

  public DecisioningData accountIdHash(String accountIdHash) {
    this.accountIdHash = accountIdHash;
    return this;
  }

  /**
   * __(OPTIONAL)__ <br> SHA-256 hash of the Cardholder?s account ID with the Token Requestor. Typically expected to be an email address.<br>  __Max Length:64__ Alpha-Numeric and Hex-encoded data (case-insensitive). 
   * @return accountIdHash
  **/
  @ApiModelProperty(example = "NA", value = "__(OPTIONAL)__ <br> SHA-256 hash of the Cardholder?s account ID with the Token Requestor. Typically expected to be an email address.<br>  __Max Length:64__ Alpha-Numeric and Hex-encoded data (case-insensitive). ")


  public String getAccountIdHash() {
    return accountIdHash;
  }

  public void setAccountIdHash(String accountIdHash) {
    this.accountIdHash = accountIdHash;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DecisioningData decisioningData = (DecisioningData) o;
    return Objects.equals(this.recommendation, decisioningData.recommendation) &&
        Objects.equals(this.recommendationAlgorithmVersion, decisioningData.recommendationAlgorithmVersion) &&
        Objects.equals(this.deviceScore, decisioningData.deviceScore) &&
        Objects.equals(this.accountScore, decisioningData.accountScore) &&
        Objects.equals(this.recommendationReasons, decisioningData.recommendationReasons) &&
        Objects.equals(this.deviceCurrentLocation, decisioningData.deviceCurrentLocation) &&
        Objects.equals(this.deviceIpAddress, decisioningData.deviceIpAddress) &&
        Objects.equals(this.mobileNumberSuffix, decisioningData.mobileNumberSuffix) &&
        Objects.equals(this.accountIdHash, decisioningData.accountIdHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recommendation, recommendationAlgorithmVersion, deviceScore, accountScore, recommendationReasons, deviceCurrentLocation, deviceIpAddress, mobileNumberSuffix, accountIdHash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DecisioningData {\n");
    
    sb.append("    recommendation: ").append(toIndentedString(recommendation)).append("\n");
    sb.append("    recommendationAlgorithmVersion: ").append(toIndentedString(recommendationAlgorithmVersion)).append("\n");
    sb.append("    deviceScore: ").append(toIndentedString(deviceScore)).append("\n");
    sb.append("    accountScore: ").append(toIndentedString(accountScore)).append("\n");
    sb.append("    recommendationReasons: ").append(toIndentedString(recommendationReasons)).append("\n");
    sb.append("    deviceCurrentLocation: ").append(toIndentedString(deviceCurrentLocation)).append("\n");
    sb.append("    deviceIpAddress: ").append(toIndentedString(deviceIpAddress)).append("\n");
    sb.append("    mobileNumberSuffix: ").append(toIndentedString(mobileNumberSuffix)).append("\n");
    sb.append("    accountIdHash: ").append(toIndentedString(accountIdHash)).append("\n");
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

