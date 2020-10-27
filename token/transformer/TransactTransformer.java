package com.fdc.mtrg.network.token.transformer;

import brave.Span;
import brave.Tracer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fdc.mtrg.api.CryptoGram;
import com.fdc.mtrg.api.CryptoGramRequest;
import com.fdc.mtrg.api.CryptoGramResponse;
import com.fdc.mtrg.api.Token;
import com.fdc.mtrg.network.token.config.ApplicationProperties;
import com.fdc.mtrg.network.token.ms.TransactRequest;
import com.fdc.mtrg.network.token.ms.TransactResponse;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactTransformer {

    private static final Logger logger = LoggerFactory.getLogger(TransactTransformer.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private Tracer tracer;

    @Transformer
    public TransactRequest doTransformRequest(@Header(Constants.PARTNER_ID) final String merchantId,
                                              @Header(Constants.TOKEN_REFERENCE_ID) final String pTokenReferenceId,
                                              Message<CryptoGramRequest> pRequestMessage) {
        logger.debug("Request received @ doTransform API for merchant Token reference Id {} and acquirerMerchantId {} ", pTokenReferenceId);

        TransactRequest transactRequest = new TransactRequest();
        CryptoGramRequest payload = pRequestMessage.getPayload();

        Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                .ifPresent(traceId -> transactRequest.setRequestId(traceId.toString()));

        transactRequest.setTokenUniqueReference(pTokenReferenceId);
        transactRequest.setDsrpType(Constants.UCAF);
        // truncating to 8 characters for now. This will be calculated for us on mastercard's side later, per Vinit.
        transactRequest.setUnpredictableNumber(UUID.randomUUID().toString().substring(28, 36));
        return transactRequest;
    }

    @Transformer
    public CryptoGramResponse doTransformResponse(@Header(Constants.PARTNER_ID) final String pTokenReferenceId,
                                                  TransactResponse pResponseMessage) throws FdcException, JsonProcessingException {
        CryptoGramResponse returnMessage = new CryptoGramResponse();
        returnMessage.setTransactionId(pResponseMessage.getResponseId());

        if (null != pResponseMessage.getEncryptedPayload()) {
            Token returnToken = new Token();
            returnToken.setPaymentToken(pResponseMessage.getEncryptedPayload().getEncryptedData().getAccountNumber());
            //TODO: we may have to return this also.
            //returnToken.setExpiryDate();
            returnMessage.setToken(returnToken);
            CryptoGram returnCrypto = new CryptoGram();
            returnCrypto.setCryptogram(pResponseMessage.getEncryptedPayload().getEncryptedData().getDe48se43Data());
            returnCrypto.setAccountNumber(pResponseMessage.getEncryptedPayload().getEncryptedData().getAccountNumber());
            returnMessage.setCrypto(returnCrypto);
        }

        Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                .ifPresent(traceId -> returnMessage.setTransactionId(traceId.toString()));

        logger.debug("card data {}", pResponseMessage.getEncryptedPayload().getEncryptedData().toString());
        return returnMessage;
    }
}
