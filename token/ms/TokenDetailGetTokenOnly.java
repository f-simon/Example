package com.fdc.mtrg.network.token.ms;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TokenDetailGetTokenOnly
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-01-16T12:40:03.173-05:00[America/New_York]")

public class TokenDetailGetTokenOnly   {
  @JsonProperty("tokenUniqueReference")
  private String tokenUniqueReference;

  @JsonProperty("publicKeyFingerprint")
  private String publicKeyFingerprint;

  @JsonProperty("encryptedKey")
  private String encryptedKey;

  @JsonProperty("oaepHashingAlgorithm")
  private String oaepHashingAlgorithm;

  @JsonProperty("iv")
  private String iv;

  @JsonProperty("encryptedData")
  private TokenDetailDataGetTokenOnly encryptedData;

  public TokenDetailGetTokenOnly tokenUniqueReference(String tokenUniqueReference) {
    this.tokenUniqueReference = tokenUniqueReference;
    return this;
  }

  /**
   * Globally unique identifier for the Token, as assigned by MDES.<br>     __Max Length:64__ 
   * @return tokenUniqueReference
  */
  public String getTokenUniqueReference() {
    return tokenUniqueReference;
  }

  public void setTokenUniqueReference(String tokenUniqueReference) {
    this.tokenUniqueReference = tokenUniqueReference;
  }

  public TokenDetailGetTokenOnly publicKeyFingerprint(String publicKeyFingerprint) {
    this.publicKeyFingerprint = publicKeyFingerprint;
    return this;
  }

  /**
   * The certificate fingerprint identifying the public key used to encrypt the ephemeral AES key.<br>     __Max Length:64__ Hex-encoded data (case-insensitive). 
   * @return publicKeyFingerprint
  */
  public String getPublicKeyFingerprint() {
    return publicKeyFingerprint;
  }

  public void setPublicKeyFingerprint(String publicKeyFingerprint) {
    this.publicKeyFingerprint = publicKeyFingerprint;
  }

  public TokenDetailGetTokenOnly encryptedKey(String encryptedKey) {
    this.encryptedKey = encryptedKey;
    return this;
  }

  /**
   * One-time use AES key encrypted by the MasterCard public key (as identified by 'publicKeyFingerprint') using the OAEP or RSA Encryption Standard PKCS #1 v1.5 scheme (depending on the value of 'oaepHashingAlgorithm'. Requirement is for a 128-bit key (with 256-bit key supported as an option).<br>     __Max Length:512__ 
   * @return encryptedKey
  */
  public String getEncryptedKey() {
    return encryptedKey;
  }

  public void setEncryptedKey(String encryptedKey) {
    this.encryptedKey = encryptedKey;
  }

  public TokenDetailGetTokenOnly oaepHashingAlgorithm(String oaepHashingAlgorithm) {
    this.oaepHashingAlgorithm = oaepHashingAlgorithm;
    return this;
  }

  /**
   * Hashing algorithm used with the OAEP scheme. If omitted, then the RSA Encryption Standard PKCS #1 v1.5 will be used. Must be either 'SHA256' (Use the SHA-256 algorithm) or 'SHA512' (Use the SHA-512 algorithm).<br>     __Max Length:6__ 
   * @return oaepHashingAlgorithm
  */
  public String getOaepHashingAlgorithm() {
    return oaepHashingAlgorithm;
  }

  public void setOaepHashingAlgorithm(String oaepHashingAlgorithm) {
    this.oaepHashingAlgorithm = oaepHashingAlgorithm;
  }

  public TokenDetailGetTokenOnly iv(String iv) {
    this.iv = iv;
    return this;
  }

  /**
   * It is recommended to supply a random initialization vector when encrypting the data using the one-time use AES key. Must be exactly 16 bytes (32 character hex string) to match the block size. Hex-encoded data (case-insensitive).  __Max Length:32__ 
   * @return iv
  */

  public String getIv() {
    return iv;
  }

  public void setIv(String iv) {
    this.iv = iv;
  }

  public TokenDetailGetTokenOnly encryptedData(TokenDetailDataGetTokenOnly encryptedData) {
    this.encryptedData = encryptedData;
    return this;
  }

  /**
   * Get encryptedData
   * @return encryptedData
  */

  @Valid
  public TokenDetailDataGetTokenOnly getEncryptedData() {
    return encryptedData;
  }

  public void setEncryptedData(TokenDetailDataGetTokenOnly encryptedData) {
    this.encryptedData = encryptedData;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenDetailGetTokenOnly tokenDetailGetTokenOnly = (TokenDetailGetTokenOnly) o;
    return Objects.equals(this.tokenUniqueReference, tokenDetailGetTokenOnly.tokenUniqueReference) &&
        Objects.equals(this.publicKeyFingerprint, tokenDetailGetTokenOnly.publicKeyFingerprint) &&
        Objects.equals(this.encryptedKey, tokenDetailGetTokenOnly.encryptedKey) &&
        Objects.equals(this.oaepHashingAlgorithm, tokenDetailGetTokenOnly.oaepHashingAlgorithm) &&
        Objects.equals(this.iv, tokenDetailGetTokenOnly.iv) &&
        Objects.equals(this.encryptedData, tokenDetailGetTokenOnly.encryptedData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokenUniqueReference, publicKeyFingerprint, encryptedKey, oaepHashingAlgorithm, iv, encryptedData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenDetailGetTokenOnly {\n");
    
    sb.append("    tokenUniqueReference: ").append(toIndentedString(tokenUniqueReference)).append("\n");
    sb.append("    publicKeyFingerprint: ").append(toIndentedString(publicKeyFingerprint)).append("\n");
    sb.append("    encryptedKey: ").append(toIndentedString(encryptedKey)).append("\n");
    sb.append("    oaepHashingAlgorithm: ").append(toIndentedString(oaepHashingAlgorithm)).append("\n");
    sb.append("    iv: ").append(toIndentedString(iv)).append("\n");
    sb.append("    encryptedData: ").append(toIndentedString(encryptedData)).append("\n");
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

