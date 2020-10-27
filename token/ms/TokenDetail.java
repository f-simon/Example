package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenDetail {

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
    @Nullable
    private TokenDetailDataTCCOnly encryptedData;

    public String getTokenUniqueReference() {
        return tokenUniqueReference;
    }

    public void setTokenUniqueReference(String tokenUniqueReference) {
        this.tokenUniqueReference = tokenUniqueReference;
    }

    public String getPublicKeyFingerprint() {
        return publicKeyFingerprint;
    }

    public void setPublicKeyFingerprint(String publicKeyFingerprint) {
        this.publicKeyFingerprint = publicKeyFingerprint;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public String getOaepHashingAlgorithm() {
        return oaepHashingAlgorithm;
    }

    public void setOaepHashingAlgorithm(String oaepHashingAlgorithm) {
        this.oaepHashingAlgorithm = oaepHashingAlgorithm;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public TokenDetailDataTCCOnly getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(TokenDetailDataTCCOnly encryptedData) {
        this.encryptedData = encryptedData;
    }

    @Override
    public String toString() {
        return "TokenDetail{" +
                "tokenUniqueReference='" + tokenUniqueReference + '\'' +
                ", publicKeyFingerprint='" + publicKeyFingerprint + '\'' +
                ", encryptedKey='" + encryptedKey + '\'' +
                ", oaepHashingAlgorithm='" + oaepHashingAlgorithm + '\'' +
                ", iv='" + iv + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenDetail that = (TokenDetail) o;
        return Objects.equals(tokenUniqueReference, that.tokenUniqueReference) &&
                Objects.equals(publicKeyFingerprint, that.publicKeyFingerprint) &&
                Objects.equals(encryptedKey, that.encryptedKey) &&
                Objects.equals(oaepHashingAlgorithm, that.oaepHashingAlgorithm) &&
                Objects.equals(iv, that.iv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenUniqueReference, publicKeyFingerprint, encryptedKey, oaepHashingAlgorithm, iv);
    }
}
