package com.fdc.mtrg.network.token.transformer;

import brave.Span;
import brave.Tracer;
import com.fdc.mtrg.api.GetAssetResponse;
import com.fdc.mtrg.api.MediaContents;
import com.fdc.mtrg.network.token.ms.AssetResponse;
import com.fdc.mtrg.network.token.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class GetCardMetadataTransformer {

    private static final Logger logger = LoggerFactory.getLogger(GetCardMetadataTransformer.class);

    @Autowired
    private Tracer tracer;

    @Transformer
    public GetAssetResponse doCardMetadataTransformResposne(@Header(Constants.PARTNER_ID) final String pmerchantId,
                                                            Message<AssetResponse> assetResponseMessage) {

        logger.debug("Request received @ doCardMetadataTransformResponse API for merchant Partner {} ", pmerchantId);

        AssetResponse response = Objects.requireNonNull(assetResponseMessage.getPayload());
        GetAssetResponse assetResponse = new GetAssetResponse();

        response.getMediaContents().forEach(mc -> {
            MediaContents mcs = new MediaContents();
            mcs.mimeType(mc.getType())
                    .data(mc.getData())
                    .height(mc.getHeight())
                    .width(mc.getWidth());
            assetResponse.setMediaContents(mcs);

        });

        Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                .ifPresent(traceId -> assetResponse.setTransactionId(traceId.toString()));

        return assetResponse;
    }
}
