package com.fdc.mtrg.network.token.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdc.mtrg.api.*;
import com.fdc.mtrg.network.token.error.ApplicationError;
import com.fdc.mtrg.network.token.ms.DecisioningData;
import com.fdc.mtrg.network.token.util.Constants;
import com.fdc.util.exception.FdcException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class TokenizeFilter {
    private static final Logger logger = LoggerFactory.getLogger(TokenizeFilter.class);


    @Autowired
    private ObjectMapper objectMapper;

    public TokenizeFilter() {
    }

    @Filter
    public boolean doValidateRequest(@Header(Constants.PARTNER_ID) final String pmerchantId,
                                     Message<ProvisionTokenRequest> pRequestMessage) throws FdcException {
        try {
            logger.debug("Request received @ doValidateRequest API for merchant Partner {} and request {} ",
                    pmerchantId, objectMapper.writeValueAsString(pRequestMessage.getPayload()));

            List<FieldError> fieldErrors = new ArrayList<>();
            ProvisionTokenRequest payload = pRequestMessage.getPayload();

            if (payload == null || payload.getProvision() == null) {
                fieldErrors.add(new FieldError(ProvisionTokenRequest.class.getName(), Constants.TOKEN_REQUEST_PAYLOAD, Constants.NULL_PAYLOAD));
            } else {
                Card card = payload.getProvision().getCard();

                if (card == null) {
                    fieldErrors.add(new FieldError(Card.class.getName(), Constants.CARD, Constants.ERROR_FORMAT));
                } else {
                    validateCard(fieldErrors, card);

                    if (card.getBillingAddress() != null) {
                        validateBillingAddress(fieldErrors, card.getBillingAddress());
                    }
                }
                validateDevice(fieldErrors, payload.getProvision().getDeviceDetails());
            }
            if (fieldErrors.size() > 0) {
                throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(),
                        ApplicationError.INVALID_REQUEST.getErrorDescription(), fieldErrors, ApplicationError.INVALID_REQUEST.getCategory(), null);
            }
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage(), ex);
            throw new FdcException(ApplicationError.INVALID_REQUEST.getErrorCode(),
                    ApplicationError.INVALID_REQUEST.getErrorDescription());

        }
        return true;
    }

    // validate optional fields if not blank
    private void validateBillingAddress(List<FieldError> fieldErrors, Address address) {

        if(!StringUtils.isBlank(address.getStreetAddress()) && address.getStreetAddress().length() > 64) {
            fieldErrors.add(new FieldError(Address.class.getName(), Constants.STREET_ADDRESS, String.format(Constants.LESS_OR_EQUAL_MESSAGE, Constants.STREET_ADDRESS, "64")));
            //MDES spec indicates a max length of 3 for Country field, but it returns error if its length is less than 3
        } else if (!StringUtils.isBlank(address.getCountry()) && address.getCountry().length() != 3) {
            fieldErrors.add(new FieldError(Address.class.getName(), Constants.COUNTRY, String.format(Constants.LENGTH_MESSAGE, Constants.COUNTRY, "3")));
        }  else if (!StringUtils.isBlank(address.getPostalCode()) && address.getPostalCode().length() > 16) {
            fieldErrors.add(new FieldError(Address.class.getName(), Constants.POSTCODE, String.format(Constants.LESS_OR_EQUAL_MESSAGE, Constants.POSTCODE, "16")));
        }  else if (!StringUtils.isBlank(address.getLocality()) && address.getLocality().length() > 32) {
            fieldErrors.add(new FieldError(Address.class.getName(), Constants.LOCALITY, String.format(Constants.LESS_OR_EQUAL_MESSAGE, Constants.LOCALITY, "32")));
        }
    }

    private void validateCard(List<FieldError> fieldErrors, Card card) {

        if (StringUtils.isBlank(card.getCardNumber())) {
            fieldErrors.add(new FieldError(Card.class.getName(), Constants.CARD_NUMBER, Constants.ERROR_FORMAT));
        } else if (card.getCardNumber().length() < 9 || card.getCardNumber().length() > 19) {
            fieldErrors.add(new FieldError(Card.class.getName(), Constants.CARD_NUMBER, Constants.CARD_NUMBER_RANGE));
        }
        if (card.getExpiryDate() == null) {
            fieldErrors.add(new FieldError(Card.class.getName(), Constants.EXPIRY_DATE, Constants.ERROR_FORMAT));
        } else {
            ExpiryDate expiryDate = card.getExpiryDate();

            if (StringUtils.isBlank(expiryDate.getYear())) {
                fieldErrors.add(new FieldError(ExpiryDate.class.getName(), Constants.YEAR, Constants.ERROR_FORMAT));
            } else if (expiryDate.getYear().length() != 2) {
                fieldErrors.add(new FieldError(ExpiryDate.class.getName(), Constants.YEAR, Constants.YEAR_LENGTH));
            } else if (!StringUtils.isNumeric(expiryDate.getYear())) {
                fieldErrors.add(new FieldError(ExpiryDate.class.getName(), Constants.YEAR, Constants.YEAR_NOT_NUMERIC));
            }

            if (StringUtils.isBlank(expiryDate.getMonth())) {
                fieldErrors.add(new FieldError(ExpiryDate.class.getName(), Constants.MONTH, Constants.ERROR_FORMAT));
            } else if (expiryDate.getMonth().length() != 2) {
                fieldErrors.add(new FieldError(ExpiryDate.class.getName(), Constants.MONTH, Constants.MONTH_LENGTH));
            } else if (!StringUtils.isNumeric(expiryDate.getMonth())) {
                fieldErrors.add(new FieldError(ExpiryDate.class.getName(), Constants.MONTH, Constants.MONTH_NOT_NUMERIC));
            }
        }
        if (card.getSecurityCode() != null) {
            if (StringUtils.isBlank(card.getSecurityCode())) {
                fieldErrors.add(new FieldError(Card.class.getName(), Constants.SECURITY_CODE, Constants.ERROR_FORMAT));
            } else if (card.getSecurityCode().length() != 3) {
                fieldErrors.add(new FieldError(Card.class.getName(), Constants.SECURITY_CODE, Constants.SECURITY_CODE_LENGTH));
            }
        }
        if (StringUtils.isNotBlank(card.getNameOnCard()) && card.getNameOnCard().length() > 27) {
            fieldErrors.add(new FieldError(Card.class.getName(), Constants.NAME_ON_CARD, String.format(Constants.LESS_OR_EQUAL_MESSAGE, Constants.NAME_ON_CARD, "27")));
        }
    }

    private void validateDevice(List<FieldError> fieldErrors, DeviceDetails device) {
        if (device != null) {
            if (StringUtils.isNotBlank(device.getDeviceScore())
                    && !StringUtils.isNumeric(device.getDeviceScore())
                    && device.getDeviceScore().length() != 1
                    && !"12345".contains(device.getDeviceScore())) {
                fieldErrors.add(new FieldError(DecisioningData.class.getName(), Constants.DEVICE_SCORE, String.format(Constants.VALUE_BETWEEN, Constants.DEVICE_SCORE, "1", "5")));
            }
            if (StringUtils.isNotBlank(device.getAccountScore())
                    && !StringUtils.isNumeric(device.getAccountScore())
                    && device.getDeviceScore().length() != 1
                    && !"12345".contains(device.getAccountScore())) {
                fieldErrors.add(new FieldError(DecisioningData.class.getName(), Constants.ACCOUNT_SCORE, String.format(Constants.VALUE_BETWEEN, Constants.ACCOUNT_SCORE, "1", "5")));
            }
            DeviceDetailsLoaction deviceLoaction = device.getLoaction();

            if (deviceLoaction != null) {
                if (StringUtils.isNotBlank(deviceLoaction.getIpAddress()) && deviceLoaction.getIpAddress().length() > 15) {
                    fieldErrors.add(new FieldError(DeviceDetails.class.getName(), Constants.DEVICE_IP_ADDRESS, String.format(Constants.DEVICE_IP_ADDRESS, Constants.DEVICE_IP_ADDRESS, "15")));
                }
                String location = String.format("%s,%s", deviceLoaction.getLatitude(), deviceLoaction.getLongitude());

                if (location.length() > 14) {
                    fieldErrors.add(new FieldError(DeviceDetails.class.getName(), Constants.DEVICE_CURRENT_LOCATION, String.format(Constants.LESS_OR_EQUAL_MESSAGE, Constants.DEVICE_CURRENT_LOCATION, "14")));
                }
            }
        }
    }
}
