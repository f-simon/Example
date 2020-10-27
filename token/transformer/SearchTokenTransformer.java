package com.fdc.mtrg.network.token.transformer;

import brave.Span;
import brave.Tracer;
import com.fdc.mtrg.api.*;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.ms.GetTokenRequestSchema;
import com.fdc.mtrg.network.token.ms.search.SearchTokensResponseSchema;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchTokenTransformer extends TokenizeTransformer {

    private static final Logger logger = LoggerFactory.getLogger(SearchTokenTransformer.class);

    @Autowired
    private Tracer tracer;

    @Transformer
    public SearchTokenResponse doTransformResponse(@Header(Constants.PARTNER_ID) final String pmerchantId,
                                                   SearchTokensResponseSchema pRequestMessage) throws FdcException {
        logger.debug("Request received @ doReplyTransform API for merchant Partner {} and request {} ", pmerchantId);

        SearchTokenResponse searchTokenResponse = new SearchTokenResponse();

        if (pRequestMessage != null) {
            List<TokenInfo> tokenInfos = new ArrayList<>();

            for (com.fdc.mtrg.network.token.ms.search.Token token : pRequestMessage.getTokens()) {
                TokenInfo tokenInfo = new TokenInfo();
                tokenInfo.setDecision(TokenStatus.valueOf(token.getStatus()));
                Token apiToken = new Token();
                apiToken.setTokenReferenceId(token.getTokenUniqueReference());
                apiToken.setTspId(TSPID.MASTERCARD.getValue());
                tokenInfo.setToken(apiToken);
                tokenInfos.add(tokenInfo);
            }

            searchTokenResponse.setTokenInfo(tokenInfos);
        }

        Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                .ifPresent(traceId -> searchTokenResponse.setTransactionId(traceId.toString()));

        return searchTokenResponse;
    }

    @Transformer
    public GetTokenRequestSchema doTransformResponseForGetTokenDetails(@Header(Constants.PARTNER_ID) final String pmerchantId,
                                                                       SearchTokensResponseSchema pRequestMessage) throws FdcException {

        logger.debug("Request received @ doTransformResponseForGetTokenDetails API for merchant Partner {} and request {} ", pmerchantId);
        GetTokenRequestSchema getTokenRequestSchema = new GetTokenRequestSchema();

        if (null != pRequestMessage && null != pRequestMessage.getTokens()) {
            getTokenRequestSchema.setTokenUniqueReference(pRequestMessage.getTokens().get(0).getTokenUniqueReference());
            getTokenRequestSchema.setIncludeTokenDetail("true");

            Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                    .ifPresent(traceId -> getTokenRequestSchema.setRequestId(traceId.toString()));
        } else {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError("TokenReferenceId", "TokenReferenceId", "Invalid TokenReferenceId"));
            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors, ApplicationError.INVALID_REQUEST.getCategory(), null);
        }
        return getTokenRequestSchema;
    }
}

