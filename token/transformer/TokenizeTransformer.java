package com.fdc.mtrg.network.token.transformer;

import brave.Span;
import brave.Tracer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.api.Token;
import com.fdc.mtrg.api.*;
import com.fdc.mtrg.network.token.entity.MerchantTridMapping;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.ms.*;
import com.fdc.mtrg.network.token.repository.MerchantTridRepository;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import com.fdc.util.logging.SimpleAroundLog;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.fdc.mtrg.network.token.cache.CacheConfigurations.MERCHANT_TRID_TSP_CACHE;

@Service
public class TokenizeTransformer {
    private static final Logger logger = LoggerFactory.getLogger(TokenizeTransformer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MerchantTridRepository merchantTridRepository;

    @Autowired
    private Tracer tracer;

    @Autowired
    private CacheManager cacheManager;

    @Transformer
    public TokenizeRequest doTransformRequest(@Header(Constants.PARTNER_ID) final String pmerchantId,
                                              Message<ProvisionTokenRequest> pRequestMessage) throws FdcException {

        TokenizeRequest tokenizeRequest = new TokenizeRequest();

        try {
            logger.debug("Request received @ doTransform API for merchant Partner {} and request {} ",
                    pmerchantId, objectMapper.writeValueAsString(pRequestMessage.getPayload()));

            ProvisionTokenRequest payload = pRequestMessage.getPayload();

            Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                    .ifPresent(traceId -> tokenizeRequest.setRequestId(traceId.toString()));

            tokenizeRequest.setTokenType(Constants.CLOUD);
            tokenizeRequest.setTaskId(UUID.randomUUID().toString());
            setTRID(pmerchantId, tokenizeRequest);
            tokenizeRequest.setFundingAccountInfo(toFundingAccountInfo(payload));
            tokenizeRequest.setDecisioningData(toDecisioningData(payload.getProvision()));

        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage(), ex);
            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(),
                    ApplicationError.INVALID_REQUEST.getErrorDescription());

        }
        return tokenizeRequest;
    }

    @SimpleAroundLog
    private void setTRID(@Header(Constants.PARTNER_ID) String pmerchantId, TokenizeRequest tokenizeRequest) throws FdcException {
        Cache cache = cacheManager.getCache(MERCHANT_TRID_TSP_CACHE);
        if (null != cache && cache.get(pmerchantId + Constants.MASTERCARD, String.class) != null) {
            tokenizeRequest.setTokenRequestorId(cache.get(pmerchantId + Constants.MASTERCARD, String.class));
        } else {
            List<MerchantTridMapping> merchantTridMappings = merchantTridRepository.findByMerchantIdAndTsp(pmerchantId, Constants.MASTERCARD);
            if (null != merchantTridMappings && merchantTridMappings.size() > 0) {
                tokenizeRequest.setTokenRequestorId(merchantTridMappings.get(0).getTokenRequestorId());
                if (null != cache)
                    cache.put(pmerchantId + Constants.MASTERCARD, merchantTridMappings.get(0).getTokenRequestorId());
            } else {
                List<FieldError> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldError("Token Requestor Id", "Token Requestor Id", "Merchant is not setup with in the system."));
                throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(),
                        ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors);
            }
        }
    }

    @Transformer
    public ProvisionTokenResponse doTransformResponse(@Header(Constants.PARTNER_ID) final String pmerchantId,
                                                      TokenizeResponse pRequestMessage) throws FdcException {

        ProvisionTokenResponse response = new ProvisionTokenResponse();

        try {
            logger.debug("Request received @ doReplyTransform API for merchant Partner {} and request {} ",
                    pmerchantId, objectMapper.writeValueAsString(pRequestMessage));

            Provision provision = new Provision();
            populate(provision, pRequestMessage);

            response.setProvision(provision);
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage(), ex);
            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(),
                    ApplicationError.INVALID_REQUEST.getErrorDescription());

        }
        return response;
    }

    //private methods for request transformation
    private DecisioningData toDecisioningData(Provision provision) {
        DeviceDetails device = provision.getDeviceDetails();
        DecisioningData decisioningData = new DecisioningData();

        if(device != null) {
            decisioningData.setRecommendationAlgorithmVersion("01");
            decisioningData.setDeviceScore(device.getDeviceScore());
            decisioningData.setAccountScore(device.getAccountScore());
            //decisioningData.setRecommendationReasons();  //not able to pick up a value without knowing recommendation

            if (provision.getAccount() != null && provision.getAccount().getEmail() != null) {
                decisioningData.setAccountIdHash(DigestUtils.sha256Hex(provision.getAccount().getEmail().getValue()));
            }
            DeviceDetailsLoaction loaction = device.getLoaction();

            if (loaction != null) {
                decisioningData.setDeviceIpAddress(loaction.getIpAddress());
                decisioningData.deviceCurrentLocation(String.format("%s,%s", loaction.getLatitude(), loaction.getLongitude()));
            }
        }
        return decisioningData;
    }
    private FundingAccountInfo toFundingAccountInfo(ProvisionTokenRequest payload) {
        FundingAccountInfo fundingAccountInfo = new FundingAccountInfo();
        EncryptedPayload encryptedPayload = new EncryptedPayload();
        EncryptedData encryptedData = new EncryptedData();
        encryptedData.setSource(payload.getProvision().getCard().getSource().getValue());
        encryptedData.setAccountHolderData(payload.getProvision().getCard().getBillingAddress() != null ? toAccountHolderData(payload.getProvision()) : null);
        encryptedData.setCardAccountData(toCardAccountData(payload.getProvision().getCard()));
        encryptedPayload.setEncryptedData(encryptedData);
        fundingAccountInfo.setEncryptedPayload(encryptedPayload);
        return fundingAccountInfo;
    }

    private CardAccountData toCardAccountData(Card card) {
        CardAccountData cardAccountData = new CardAccountData();
        cardAccountData.setAccountNumber(card.getCardNumber());
        cardAccountData.setExpiryMonth(card.getExpiryDate().getMonth());
        cardAccountData.setExpiryYear(card.getExpiryDate().getYear());
        cardAccountData.setSecurityCode(card.getSecurityCode());
        return cardAccountData;
    }

    private AccountHolderData toAccountHolderData(Provision provision) {
        AccountHolderData accountHolderData = new AccountHolderData();

        AccountHolderAddress accountHolderAddress = new AccountHolderAddress();
        Address address = provision.getCard().getBillingAddress();
        accountHolderAddress.setCity(address.getLocality());
        accountHolderAddress.setCountry(address.getCountry());
        accountHolderAddress.setLine1(address.getStreetAddress());
        accountHolderAddress.setPostalCode(address.getPostalCode());

        AccountHolderMobilePhoneNumber accountHolderMobilePhoneNumber = new AccountHolderMobilePhoneNumber();

        accountHolderMobilePhoneNumber.setCountryDialInCode("0044"); //TODO
        accountHolderData.setAccountHolderMobilePhoneNumber(accountHolderMobilePhoneNumber);

        accountHolderData.setAccountHolderAddress(accountHolderAddress);

        if (provision.getAccount() != null && provision.getAccount().getEmail() != null) {
            accountHolderData.setAccountHolderEmailAddress(provision.getAccount().getEmail().getValue());
        }
        accountHolderData.setAccountHolderName(provision.getCard().getNameOnCard());
        accountHolderData.setAccountHolderMobilePhoneNumber(accountHolderMobilePhoneNumber);
        //accountHolderData.setConsumerIdentifier();

        return accountHolderData;
    }

    private void populate(Provision provision, TokenizeResponse response) {
        TokenInfo tokenInfo = new TokenInfo();
        Card card = new Card();
        Token apiToken = new Token();

        apiToken.setTspId(TSPID.MASTERCARD.getValue());
        tokenInfo.setDecision(TokenStatus.valueOf(response.getDecision()));
        TokenDetail tokenDetail = response.getTokenDetail();
        if (tokenDetail != null) {
            apiToken.setTokenReferenceId(tokenDetail.getTokenUniqueReference());

            TokenDetailDataTCCOnly encryptedData = tokenDetail.getEncryptedData();
            if (encryptedData != null) {
                apiToken.setPaymentAccountReferenceId(encryptedData.getPaymentAccountReference());
            }
        }
        if (response.getTokenInfo() != null) {
            card.setAlias(response.getTokenInfo().getAccountPanSuffix());
            apiToken.setAlias(response.getTokenInfo().getTokenPanSuffix());

            Optional<String> tokenExpiry = Optional.ofNullable(response.getTokenInfo().getTokenExpiry());
            if (tokenExpiry.isPresent()) {
                ExpiryDate expiryDate = new ExpiryDate();
                String expiryMonth = tokenExpiry.get().substring(0, 2);
                String expiryYear = tokenExpiry.get().substring(2, 4);
                expiryDate.setMonth(expiryMonth);
                expiryDate.setYear(expiryYear);
                apiToken.setExpiryDate(expiryDate);
            }

            Optional<String> accountPanExpiry = Optional.ofNullable(response.getTokenInfo().getAccountPanExpiry());
            if (accountPanExpiry.isPresent()) {
                ExpiryDate expiryDate = new ExpiryDate();
                String expiryMonth = accountPanExpiry.get().substring(0, 2);
                String expiryYear = accountPanExpiry.get().substring(2, 4);
                expiryDate.setMonth(expiryMonth);
                expiryDate.setYear(expiryYear);
                card.setExpiryDate(expiryDate);
            }
        }
        ProductConfig productConfig = response.getProductConfig();
        if (productConfig != null) {
            card.setCardBrandLogoAssetId(productConfig.getBrandLogoAssetId());
            card.setCardBrandDescription(productConfig.getShortDescription());
        }

        Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                .ifPresent(traceId -> provision.setTransactionId(traceId.toString()));

        tokenInfo.setToken(apiToken);
        provision.setTokenInfo(tokenInfo);
        provision.setCard(card);
    }
}
