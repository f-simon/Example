package com.fdc.mtrg.network.token.ms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductConfig {
    @JsonProperty("brandLogoAssetId")
    private String brandLogoAssetId;

    @JsonProperty("issuerLogoAssetId")
    private String issuerLogoAssetId;

    @JsonProperty("isCoBranded")
    private String isCoBranded;

    @JsonProperty("coBrandName")
    private String coBrandName;

    @JsonProperty("coBrandLogoAssetId")
    private String coBrandLogoAssetId;

    @JsonProperty("cardBackgroundCombinedAssetId")
    private String cardBackgroundCombinedAssetId;

    @JsonProperty("cardBackgroundAssetId")
    private String cardBackgroundAssetId;

    @JsonProperty("iconAssetId")
    private String iconAssetId;

    @JsonProperty("foregroundColor")
    private String foregroundColor;

    @JsonProperty("issuerName")
    private String issuerName;

    @JsonProperty("shortDescription")
    private String shortDescription;

    @JsonProperty("longDescription")
    private String longDescription;

    @JsonProperty("customerServiceUrl")
    private String customerServiceUrl;

    @JsonProperty("customerServiceEmail")
    private String customerServiceEmail;

    @JsonProperty("customerServicePhoneNumber")
    private String customerServicePhoneNumber;

    @JsonProperty("issuerMobileApp")
    private Object issuerMobileApp;

    @JsonProperty("onlineBankingLoginUrl")
    private String onlineBankingLoginUrl;

    @JsonProperty("responseHost")
    private String termsAndConditionsUrl;

    @JsonProperty("privacyPolicyUrl")
    private String privacyPolicyUrl;

    @JsonProperty("issuerProductConfigCode")
    private String issuerProductConfigCode;

    public String getBrandLogoAssetId() {
        return brandLogoAssetId;
    }

    public void setBrandLogoAssetId(String brandLogoAssetId) {
        this.brandLogoAssetId = brandLogoAssetId;
    }

    public String getIssuerLogoAssetId() {
        return issuerLogoAssetId;
    }

    public void setIssuerLogoAssetId(String issuerLogoAssetId) {
        this.issuerLogoAssetId = issuerLogoAssetId;
    }

    public String getIsCoBranded() {
        return isCoBranded;
    }

    public void setIsCoBranded(String isCoBranded) {
        this.isCoBranded = isCoBranded;
    }

    public String getCoBrandName() {
        return coBrandName;
    }

    public void setCoBrandName(String coBrandName) {
        this.coBrandName = coBrandName;
    }

    public String getCoBrandLogoAssetId() {
        return coBrandLogoAssetId;
    }

    public void setCoBrandLogoAssetId(String coBrandLogoAssetId) {
        this.coBrandLogoAssetId = coBrandLogoAssetId;
    }

    public String getCardBackgroundCombinedAssetId() {
        return cardBackgroundCombinedAssetId;
    }

    public void setCardBackgroundCombinedAssetId(String cardBackgroundCombinedAssetId) {
        this.cardBackgroundCombinedAssetId = cardBackgroundCombinedAssetId;
    }

    public String getCardBackgroundAssetId() {
        return cardBackgroundAssetId;
    }

    public void setCardBackgroundAssetId(String cardBackgroundAssetId) {
        this.cardBackgroundAssetId = cardBackgroundAssetId;
    }

    public String getIconAssetId() {
        return iconAssetId;
    }

    public void setIconAssetId(String iconAssetId) {
        this.iconAssetId = iconAssetId;
    }

    public String getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(String foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getCustomerServiceUrl() {
        return customerServiceUrl;
    }

    public void setCustomerServiceUrl(String customerServiceUrl) {
        this.customerServiceUrl = customerServiceUrl;
    }

    public String getCustomerServiceEmail() {
        return customerServiceEmail;
    }

    public void setCustomerServiceEmail(String customerServiceEmail) {
        this.customerServiceEmail = customerServiceEmail;
    }

    public String getCustomerServicePhoneNumber() {
        return customerServicePhoneNumber;
    }

    public void setCustomerServicePhoneNumber(String customerServicePhoneNumber) {
        this.customerServicePhoneNumber = customerServicePhoneNumber;
    }

    public Object getIssuerMobileApp() {
        return issuerMobileApp;
    }

    public void setIssuerMobileApp(Object issuerMobileApp) {
        this.issuerMobileApp = issuerMobileApp;
    }

    public String getOnlineBankingLoginUrl() {
        return onlineBankingLoginUrl;
    }

    public void setOnlineBankingLoginUrl(String onlineBankingLoginUrl) {
        this.onlineBankingLoginUrl = onlineBankingLoginUrl;
    }

    public String getTermsAndConditionsUrl() {
        return termsAndConditionsUrl;
    }

    public void setTermsAndConditionsUrl(String termsAndConditionsUrl) {
        this.termsAndConditionsUrl = termsAndConditionsUrl;
    }

    public String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
        this.privacyPolicyUrl = privacyPolicyUrl;
    }

    public String getIssuerProductConfigCode() {
        return issuerProductConfigCode;
    }

    public void setIssuerProductConfigCode(String issuerProductConfigCode) {
        this.issuerProductConfigCode = issuerProductConfigCode;
    }

    @Override
    public String toString() {
        return "ProductConfig{" +
                "brandLogoAssetId='" + brandLogoAssetId + '\'' +
                ", issuerLogoAssetId='" + issuerLogoAssetId + '\'' +
                ", isCoBranded='" + isCoBranded + '\'' +
                ", coBrandName='" + coBrandName + '\'' +
                ", coBrandLogoAssetId='" + coBrandLogoAssetId + '\'' +
                ", cardBackgroundCombinedAssetId='" + cardBackgroundCombinedAssetId + '\'' +
                ", cardBackgroundAssetId='" + cardBackgroundAssetId + '\'' +
                ", iconAssetId='" + iconAssetId + '\'' +
                ", foregroundColor='" + foregroundColor + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", customerServiceUrl='" + customerServiceUrl + '\'' +
                ", customerServiceEmail='" + customerServiceEmail + '\'' +
                ", customerServicePhoneNumber='" + customerServicePhoneNumber + '\'' +
                ", issuerMobileApp=" + issuerMobileApp +
                ", onlineBankingLoginUrl='" + onlineBankingLoginUrl + '\'' +
                ", termsAndConditionsUrl='" + termsAndConditionsUrl + '\'' +
                ", privacyPolicyUrl='" + privacyPolicyUrl + '\'' +
                ", issuerProductConfigCode='" + issuerProductConfigCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductConfig that = (ProductConfig) o;
        return Objects.equals(brandLogoAssetId, that.brandLogoAssetId) &&
                Objects.equals(issuerLogoAssetId, that.issuerLogoAssetId) &&
                Objects.equals(isCoBranded, that.isCoBranded) &&
                Objects.equals(coBrandName, that.coBrandName) &&
                Objects.equals(coBrandLogoAssetId, that.coBrandLogoAssetId) &&
                Objects.equals(cardBackgroundCombinedAssetId, that.cardBackgroundCombinedAssetId) &&
                Objects.equals(cardBackgroundAssetId, that.cardBackgroundAssetId) &&
                Objects.equals(iconAssetId, that.iconAssetId) &&
                Objects.equals(foregroundColor, that.foregroundColor) &&
                Objects.equals(issuerName, that.issuerName) &&
                Objects.equals(shortDescription, that.shortDescription) &&
                Objects.equals(longDescription, that.longDescription) &&
                Objects.equals(customerServiceUrl, that.customerServiceUrl) &&
                Objects.equals(customerServiceEmail, that.customerServiceEmail) &&
                Objects.equals(customerServicePhoneNumber, that.customerServicePhoneNumber) &&
                Objects.equals(issuerMobileApp, that.issuerMobileApp) &&
                Objects.equals(onlineBankingLoginUrl, that.onlineBankingLoginUrl) &&
                Objects.equals(termsAndConditionsUrl, that.termsAndConditionsUrl) &&
                Objects.equals(privacyPolicyUrl, that.privacyPolicyUrl) &&
                Objects.equals(issuerProductConfigCode, that.issuerProductConfigCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandLogoAssetId, issuerLogoAssetId, isCoBranded, coBrandName, coBrandLogoAssetId, cardBackgroundCombinedAssetId, cardBackgroundAssetId, iconAssetId, foregroundColor, issuerName, shortDescription, longDescription, customerServiceUrl, customerServiceEmail, customerServicePhoneNumber, issuerMobileApp, onlineBankingLoginUrl, termsAndConditionsUrl, privacyPolicyUrl, issuerProductConfigCode);
    }
}
