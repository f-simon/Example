package com.fdc.mtrg.network.token.transformer;

import brave.Span;
import brave.Tracer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.api.*;
import com.fdc.mtrg.network.token.ms.GetTokenRequestSchema;
import com.fdc.mtrg.network.token.ms.GetTokenResponseSchema;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetTokenTransformer {
    private static final Logger logger = LoggerFactory.getLogger(GetTokenTransformer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Tracer tracer;

    @Transformer
    public GetTokenRequestSchema doTransformRequest(@Header(Constants.PARTNER_ID) final String merchantId,
                                                    @Header(Constants.TOKEN_REFERENCE_ID) final String tokenReferenceId) {

        logger.debug("Request received @ doReplyTransform API for merchant Partner {} and tokenReferenceId {} ", merchantId, tokenReferenceId);

        GetTokenRequestSchema getTokenRequestSchema = new GetTokenRequestSchema();

        Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                .ifPresent(traceId -> getTokenRequestSchema.setRequestId(traceId.toString()));

        getTokenRequestSchema.setTokenUniqueReference(tokenReferenceId);
        getTokenRequestSchema.setIncludeTokenDetail("true");

        return getTokenRequestSchema;
    }

    @Transformer
    public ProvisionTokenResponse doTransformResposne(@Header(Constants.PARTNER_ID) final String merchantId,
                                                      GetTokenResponseSchema getTokenResponseSchema) throws FdcException, JsonProcessingException {

        logger.debug("Request received @ doReplyTransform API for merchant Partner {} and request {} ", merchantId, objectMapper.writeValueAsString(getTokenResponseSchema));

        ProvisionTokenResponse provisionTokenResponse = new ProvisionTokenResponse();
        Provision provision = new Provision();
        Card card = new Card();
        Token token = new Token();
        TokenInfo tokenInfo = new TokenInfo();

        if (null != getTokenResponseSchema.getTokenDetail()) {

            Optional<String> tuf = Optional.ofNullable(getTokenResponseSchema.getTokenDetail().getTokenUniqueReference());
            if (tuf.isPresent()) {
                token.setTokenReferenceId(tuf.get());
            }
            token.setTspId(TSPID.MASTERCARD.getValue());

            if (null != getTokenResponseSchema.getTokenDetail().getEncryptedData()) {
                token.setPaymentToken(getTokenResponseSchema.getTokenDetail().getEncryptedData().getTokenNumber());

                Optional<String> paymentAccountReferenceId = Optional.ofNullable(getTokenResponseSchema.getTokenDetail().getEncryptedData().getPaymentAccountReference());
                if (paymentAccountReferenceId.isPresent()) {
                    token.setPaymentAccountReferenceId(paymentAccountReferenceId.get());
                }
            }
        }

        if (null != getTokenResponseSchema.getToken()) {

            if (null != getTokenResponseSchema.getToken().getProductConfig()) {
                Optional<String> brandLogoAssetId = Optional.ofNullable(getTokenResponseSchema.getToken().getProductConfig().getBrandLogoAssetId());
                Optional<String> longDescription = Optional.ofNullable(getTokenResponseSchema.getToken().getProductConfig().getLongDescription());

                if (brandLogoAssetId.isPresent() && longDescription.isPresent()) {
                    card.setCardBrandLogoAssetId(brandLogoAssetId.get());
                    card.setCardBrandDescription(longDescription.get());
                }
            }

            if (null != getTokenResponseSchema.getToken().getTokenInfo()) {
                Optional<String> tokenPanSuffix = Optional.ofNullable(getTokenResponseSchema.getToken().getTokenInfo().getTokenPanSuffix());
                if (tokenPanSuffix.isPresent()) {
                    token.setAlias(tokenPanSuffix.get());
                }

                Optional<String> tokenExpiry = Optional.ofNullable(getTokenResponseSchema.getToken().getTokenInfo().getTokenExpiry());
                if (tokenExpiry.isPresent()) {
                    ExpiryDate expiryDate = new ExpiryDate();
                    String expiryMonth = tokenExpiry.get().substring(0, 2);
                    String expiryYear = tokenExpiry.get().substring(2, 4);
                    expiryDate.setMonth(expiryMonth);
                    expiryDate.setYear(expiryYear);
                    token.setExpiryDate(expiryDate);
                }


                Optional<String> accountPanSuffix = Optional.ofNullable(getTokenResponseSchema.getToken().getTokenInfo().getAccountPanSuffix());
                if (tokenPanSuffix.isPresent()) {
                    card.setAlias(accountPanSuffix.get());
                }

                Optional<String> accountPanExpiry = Optional.ofNullable(getTokenResponseSchema.getToken().getTokenInfo().getAccountPanExpiry());
                if (accountPanExpiry.isPresent()) {
                    ExpiryDate expiryDate = new ExpiryDate();
                    String expiryMonth = accountPanExpiry.get().substring(0, 2);
                    String expiryYear = accountPanExpiry.get().substring(2, 4);
                    expiryDate.setMonth(expiryMonth);
                    expiryDate.setYear(expiryYear);
                    card.setExpiryDate(expiryDate);
                }
            }
        }

        Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                .ifPresent(traceId -> provision.setTransactionId(traceId.toString()));

        tokenInfo.setToken(token);
        provision.setTokenInfo(tokenInfo);
        provision.setCard(card);
        provision.setTransactionId(getTokenResponseSchema.getResponseId());
        provision.getTokenInfo().setToken(token);

        provisionTokenResponse.setProvision(provision);
        return provisionTokenResponse;
    }
}
