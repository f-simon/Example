package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EncryptedPayload implements Serializable {

    @JsonProperty("encryptedData")
    private EncryptedData encryptedData;

    @JsonProperty("publicKeyFingerprint")
    private String publicKeyFingerprint;

    @JsonProperty("encryptedKey")
    private String encryptedKey;

    @JsonProperty("oaepHashingAlgorithm")
    private String oaepHashingAlgorithm;

    @JsonProperty("iv")
    private String iv;

    public EncryptedData getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(EncryptedData encryptedData) {
        this.encryptedData = encryptedData;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncryptedPayload)) return false;
        EncryptedPayload that = (EncryptedPayload) o;
        return Objects.equals(getEncryptedData(), that.getEncryptedData()) &&
                Objects.equals(getPublicKeyFingerprint(), that.getPublicKeyFingerprint()) &&
                Objects.equals(getEncryptedKey(), that.getEncryptedKey()) &&
                Objects.equals(getOaepHashingAlgorithm(), that.getOaepHashingAlgorithm()) &&
                Objects.equals(getIv(), that.getIv());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEncryptedData(), getPublicKeyFingerprint(), getEncryptedKey(), getOaepHashingAlgorithm(), getIv());
    }

    @Override
    public String toString() {
        return "EncryptedPayload{" +
                "encryptedData='" + encryptedData + '\'' +
                ", publicKeyFingerprint='" + publicKeyFingerprint + '\'' +
                ", encryptedKey='" + encryptedKey + '\'' +
                ", oaepHashingAlgorithm='" + oaepHashingAlgorithm + '\'' +
                ", iv='" + iv + '\'' +
                '}';
    }
}
