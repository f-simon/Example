package com.fdc.mtrg.network.token.gateway;

import com.fdc.mtrg.api.*;
import com.fdc.mtrg.network.token.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

public interface ApplicationGateway {

    HttpStatus doLifeCycle(@Header(Constants.PARTNER_ID) final String merchantId,
                           @Header(Constants.TOKEN_REFERENCE_ID) final String tokenReferenceId,
                           Message<UpdateTokenRequest> lifeCycleMessage);

    @Payload("''")
    ProvisionTokenResponse doGetToken(@Header(Constants.PARTNER_ID) final String merchantId,
                                      @Header(Constants.TOKEN_REFERENCE_ID) final String tokenReferenceId);


    ProvisionTokenResponse tokenize(@Header(Constants.PARTNER_ID) final String merchantId,
                                    Message<ProvisionTokenRequest> pRequestMessage);

    ProvisionTokenResponse searchTokens(@Header(Constants.PARTNER_ID) final String merchantId,
                                     Message<ProvisionTokenRequest> pRequestMessage);

    @Payload("''")
    GetAssetResponse getCardMetadata(@Header(Constants.PARTNER_ID) final String merchantId,
                                     @Header(Constants.ASSET_ID) final String assetId);

    CryptoGramResponse transact(@Header(Constants.PARTNER_ID) final String merchantId,
                                @Header(Constants.TOKEN_REFERENCE_ID) final String tokenReferenceId,
                                Message<CryptoGramRequest> pRequestMessage);

}
