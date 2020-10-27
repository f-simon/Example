package com.fdc.mtrg.network.token.ms;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TokenDetailDataGetTokenOnly
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-01-16T12:40:03.173-05:00[America/New_York]")

public class TokenDetailDataGetTokenOnly   {
  @JsonProperty("tokenNumber")
  private String tokenNumber;

  @JsonProperty("expiryMonth")
  private String expiryMonth;

  @JsonProperty("expiryYear")
  private String expiryYear;

  @JsonProperty("accountHolderData")
  private AccountHolderDataOutbound accountHolderData;

  @JsonProperty("paymentAccountReference")
  private String paymentAccountReference;

  @JsonProperty("cardAccountData")
  private CardAccountDataOutbound cardAccountData;

  public TokenDetailDataGetTokenOnly tokenNumber(String tokenNumber) {
    this.tokenNumber = tokenNumber;
    return this;
  }

  /**
   * The Token Primary Account Number of the Card.  <br>__Max Length: 19__ <br>__Min Length: 9__ 
   * @return tokenNumber
  */
  public String getTokenNumber() {
    return tokenNumber;
  }

  public void setTokenNumber(String tokenNumber) {
    this.tokenNumber = tokenNumber;
  }

  public TokenDetailDataGetTokenOnly expiryMonth(String expiryMonth) {
    this.expiryMonth = expiryMonth;
    return this;
  }

  /**
   * The month of the token expiration date. <br> __Max Length: 2__ 
   * @return expiryMonth
  */
  public String getExpiryMonth() {
    return expiryMonth;
  }

  public void setExpiryMonth(String expiryMonth) {
    this.expiryMonth = expiryMonth;
  }

  public TokenDetailDataGetTokenOnly expiryYear(String expiryYear) {
    this.expiryYear = expiryYear;
    return this;
  }

  /**
   * The year of the token expiration date. <br> __Max Length: 2__ 
   * @return expiryYear
  */
  public String getExpiryYear() {
    return expiryYear;
  }

  public void setExpiryYear(String expiryYear) {
    this.expiryYear = expiryYear;
  }

  public TokenDetailDataGetTokenOnly accountHolderData(AccountHolderDataOutbound accountHolderData) {
    this.accountHolderData = accountHolderData;
    return this;
  }

  /**
   * Get accountHolderData
   * @return accountHolderData
  */

  @Valid
  public AccountHolderDataOutbound getAccountHolderData() {
    return accountHolderData;
  }

  public void setAccountHolderData(AccountHolderDataOutbound accountHolderData) {
    this.accountHolderData = accountHolderData;
  }

  public TokenDetailDataGetTokenOnly paymentAccountReference(String paymentAccountReference) {
    this.paymentAccountReference = paymentAccountReference;
    return this;
  }

  /**
   * The unique account reference assigned to the PAN. Conditionally returned if the Token Requestor has opted to receive PAR and providing PAR is assigned by Mastercard or the Issuer provides PAR in the authorization message response. <br>    __Max Length: 29__ 
   * @return paymentAccountReference
  */
  public String getPaymentAccountReference() {
    return paymentAccountReference;
  }

  public void setPaymentAccountReference(String paymentAccountReference) {
    this.paymentAccountReference = paymentAccountReference;
  }

  public TokenDetailDataGetTokenOnly cardAccountData(CardAccountDataOutbound cardAccountData) {
    this.cardAccountData = cardAccountData;
    return this;
  }

  /**
   * Get cardAccountData
   * @return cardAccountData
  */

  @Valid
  public CardAccountDataOutbound getCardAccountData() {
    return cardAccountData;
  }

  public void setCardAccountData(CardAccountDataOutbound cardAccountData) {
    this.cardAccountData = cardAccountData;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenDetailDataGetTokenOnly tokenDetailDataGetTokenOnly = (TokenDetailDataGetTokenOnly) o;
    return Objects.equals(this.tokenNumber, tokenDetailDataGetTokenOnly.tokenNumber) &&
        Objects.equals(this.expiryMonth, tokenDetailDataGetTokenOnly.expiryMonth) &&
        Objects.equals(this.expiryYear, tokenDetailDataGetTokenOnly.expiryYear) &&
        Objects.equals(this.accountHolderData, tokenDetailDataGetTokenOnly.accountHolderData) &&
        Objects.equals(this.paymentAccountReference, tokenDetailDataGetTokenOnly.paymentAccountReference) &&
        Objects.equals(this.cardAccountData, tokenDetailDataGetTokenOnly.cardAccountData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokenNumber, expiryMonth, expiryYear, accountHolderData, paymentAccountReference, cardAccountData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenDetailDataGetTokenOnly {\n");
    
    sb.append("    tokenNumber: ").append(toIndentedString(tokenNumber)).append("\n");
    sb.append("    expiryMonth: ").append(toIndentedString(expiryMonth)).append("\n");
    sb.append("    expiryYear: ").append(toIndentedString(expiryYear)).append("\n");
    sb.append("    accountHolderData: ").append(toIndentedString(accountHolderData)).append("\n");
    sb.append("    paymentAccountReference: ").append(toIndentedString(paymentAccountReference)).append("\n");
    sb.append("    cardAccountData: ").append(toIndentedString(cardAccountData)).append("\n");
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

