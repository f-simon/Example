package com.fdc.mtrg.network.token.endpoint;


import com.fdc.mtrg.api.CryptoGramRequest;
import com.fdc.mtrg.api.GetAssetResponse;
import com.fdc.mtrg.api.ProvisionTokenRequest;
import com.fdc.mtrg.api.UpdateTokenRequest;
import com.fdc.mtrg.network.token.gateway.ApplicationGateway;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import com.fdc.util.logging.AroundLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/v1/merchants/{merchantId}")
public class ApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationGateway applicationGateway;

    @AroundLog
    @PostMapping(path = "/provision-tokens", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> doTokenization(@RequestBody final ProvisionTokenRequest tokenRequest,
                                            @RequestParam(value = Constants.SEARCH, required = false) final boolean search,
                                            @PathVariable final String merchantId,
                                            @RequestHeader(value = Constants.CLIENT_REQUEST_ID, required = true) final String clientRequestId,
                                            HttpServletResponse response) {

        logger.debug("Request received @ tokenize API for merchant Partner {}", merchantId);

        ResponseEntity<?> responseEntity;

        if (search) {
            responseEntity = new ResponseEntity<>(applicationGateway.searchTokens(merchantId, MessageBuilder.withPayload(tokenRequest).build()), HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(applicationGateway.tokenize(merchantId, MessageBuilder.withPayload(tokenRequest).build()), HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @AroundLog
    @PatchMapping(path = "/provision-tokens/{tokenReferenceId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> lifeCycle(@RequestBody final UpdateTokenRequest lifeCycle,
                                       @PathVariable final String merchantId,
                                       @PathVariable final String tokenReferenceId,
                                       @RequestHeader(value = Constants.CLIENT_REQUEST_ID, required = true) final String clientRequestId) throws SpelEvaluationException {

        logger.debug("Request received @ dolifeCycle API for merchantId {}  and tokenReferenceId {}", merchantId, tokenReferenceId);

        return new ResponseEntity<>(applicationGateway.doLifeCycle(merchantId, tokenReferenceId, MessageBuilder.withPayload(lifeCycle).build()));
    }

    @AroundLog
    @GetMapping(path = "/provision-tokens/{tokenReferenceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getToken(@PathVariable final String merchantId,
                                      @PathVariable final String tokenReferenceId,
                                      @RequestHeader(value = Constants.CLIENT_REQUEST_ID, required = true) final String clientRequestId) {

        logger.debug("Request received @ dogetToken API for merchantId {}", merchantId);

        return new ResponseEntity<>(applicationGateway.doGetToken(merchantId, tokenReferenceId), HttpStatus.OK);
    }

    @AroundLog
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/provision-tokens/assets/{assetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCardMetadata(@PathVariable("merchantId") final String merchantId,
                                             @PathVariable("assetId") final String assetId,
                                             @RequestHeader(value = Constants.CLIENT_REQUEST_ID, required = true) final String clientRequestId) throws FdcException {

        logger.debug("Request received @ getCardMetadata API for merchant Partner {}", merchantId);

        return new ResponseEntity<GetAssetResponse>(applicationGateway.getCardMetadata(merchantId, assetId), HttpStatus.OK);
    }

    @PostMapping(path = "/provision-tokens/{tokenReferenceId}/cryptograms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> transact(@PathVariable("merchantId") final String merchantId,
                                      @PathVariable("tokenReferenceId") final String tokenReferenceId,
                                      @RequestBody final CryptoGramRequest cryptogramRequest,
                                      @RequestHeader(value = Constants.CLIENT_REQUEST_ID, required = true) final String clientRequestId) throws FdcException {

        logger.debug("Request received @ transact API for tokenReferenceId {}", tokenReferenceId);

        return new ResponseEntity<>(applicationGateway.transact(merchantId, tokenReferenceId, MessageBuilder.withPayload(cryptogramRequest).build()), HttpStatus.CREATED);
    }
}
