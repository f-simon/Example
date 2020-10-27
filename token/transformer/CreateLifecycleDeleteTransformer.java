package com.fdc.mtrg.network.token.transformer;

import brave.Span;
import brave.Tracer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.api.UpdateTokenRequest;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.ms.DeleteRequestSchema;
import com.fdc.mtrg.network.token.ms.DeleteResponseSchema;
import com.fdc.mtrg.network.token.ms.TokenForLCM;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import com.fdc.util.exception.types.FdcSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateLifecycleDeleteTransformer {
    private static final Logger logger = LoggerFactory.getLogger(CreateLifecycleDeleteTransformer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Tracer tracer;

    @Transformer
    public DeleteRequestSchema doTransformRequest(@Header(Constants.PARTNER_ID) final String merchantId,
                                                  @Header(Constants.TOKEN_REFERENCE_ID) final String tokenReferenceId,
                                                  Message<UpdateTokenRequest> updateTokenRequest)
            throws JsonProcessingException {

        logger.debug("Request received @ doReplyTransform API for merchant Partner {} : tokenRefereneId  {} and request {} ", merchantId,
                tokenReferenceId, objectMapper.writeValueAsString(updateTokenRequest.getPayload()));

        UpdateTokenRequest payload = updateTokenRequest.getPayload();
        DeleteRequestSchema deleteRequestSchema = new DeleteRequestSchema();

        Optional.ofNullable(tracer).map(Tracer::currentSpan).map(Span::context)
                .ifPresent(traceId -> deleteRequestSchema.setRequestId(traceId.toString()));

        var tuf = new ArrayList<String>();
        tuf.add(tokenReferenceId);
        deleteRequestSchema.setTokenUniqueReferences(tuf);
        deleteRequestSchema.setReason(payload.getUpdateReason().getReason());
        deleteRequestSchema.setReasonCode(payload.getUpdateReason().getReasonCode().getValue());
        deleteRequestSchema.setCausedBy(payload.getUpdateReason().getCausedBy().getValue());

        return deleteRequestSchema;
    }

    @Transformer
    public HttpStatus doTransformResposne(@Header(Constants.PARTNER_ID) final String merchantId,
                                          DeleteResponseSchema lifecycleMessage) throws JsonProcessingException, FdcException {

        logger.debug("Request received @ doReplyTransform API for merchant Partner {} and request {} ", merchantId, objectMapper.writeValueAsString(lifecycleMessage));

        List<TokenForLCM> tokens = lifecycleMessage.getTokens();
        if (null != tokens && tokens.size() > 0) {
            if (tokens.get(0).getStatus().equalsIgnoreCase(Constants.DEACTIVATED)) {
                return HttpStatus.NO_CONTENT;
            } else {
                List<FieldError> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldError("StatusUpdate", "Status", "Status update failed"));
                throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(), ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors, ApplicationError.INVALID_REQUEST.getCategory(), null);
            }
        } else {
            logger.error("TokenInfo is null from MDES.");
            throw new FdcSystemException(ApplicationError.SERVER_ERROR.getErrorCode(), ApplicationError.SERVER_ERROR.getErrorDescription());
        }
    }
}
