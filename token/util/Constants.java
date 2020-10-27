package com.fdc.mtrg.network.token.util;

public interface Constants {
    String CLIENT_REQUEST_ID = "Client-Request-Id";
    String PARTNER_ID = "merchantId";
    String TOKEN_REFERENCE_ID = "tokenReferenceId";
    String ASSET_ID = "assetId";
    String CLOUD = "CLOUD";
    String TOKENIZATION_URI = "digitization/static/1/0/tokenize";
    String TRANSACT_URI = "remotetransaction/static/1/0/transact";
    String SUSPEND_URI = "digitization/static/1/0/suspend";
    String UNSUSPEND_URI = "digitization/static/1/0/unsuspend";
    String DELETE_URI = "digitization/static/1/0/delete";
    String GETTOKEN = "digitization/static/1/0/getToken";
    String SEARCH_TOKENS_URI = "digitization/static/1/0/searchTokens";
    String ACCCEPT = "Accept";
    String APPLICATION_JSON = "application/json;charset=UTF-8";
    String CONTENT_TYPE = "Content-Type";
    String CONTENT_TYPE_JSON = "application/json";
    String CARD_METADATA_URI = "assets/static/1/0/asset/";
    String ACTION_ID = "actions";
    String GET_TASK_STATUS_URI = "digitization/static/1/0/getTaskStatus";
    String TOKENIZE_APPROVED = "APPROVED";
    String UCAF = "UCAF";
    String SUSPENDED = "SUSPENDED";
    String ACTIVE = "ACTIVE";
    String DEACTIVATED = "DEACTIVATED";
    String TOKEN_REQUEST_PAYLOAD = "tokenRequestPayload";
    String TRANSACT_REQUEST_PAYLOAD = "transactRequestPayload";
    String MASTERCARD = "MASTERCARD";
    String TOKEN_REQUEST_ID = "tokenRequestorId";
    String CARD = "card";
    String FUNDING_SOURCE_TYPE = "fundingSourceType";
    String CARD_NUMBER = "cardNumber";
    String EXPIRY_DATE = "expiryDate";
    String YEAR = "year";
    String MONTH = "month";
    String SECURITY_CODE = "securityCode";

    String STREET_ADDRESS = "streetAddress";
    String COUNTRY = "country";
    String LOCALITY = "locality";
    String POSTCODE = "postalCode";
    String NAME_ON_CARD = "nameOnCard";

    String DEVICE_SCORE = "deviceScore";
    String ACCOUNT_SCORE = "accountScore";
    String DEVICE_CURRENT_LOCATION = "deviceCurrentLocation";
    String DEVICE_IP_ADDRESS = "deviceIpAddress";

    //field descriptions
    String ERROR_FORMAT = "This is a required field and cannot be empty.";
    String NULL_PAYLOAD = "Payload cannot be null.";
    String NOT_NUMERIC = "TokenRequestorId must be numeric.";
    String LENGTH_MESSAGE = "%s must be %s in length.";
    String LESS_OR_EQUAL_MESSAGE = "%s must be less than or equal to %s in length.";
    String CARD_NUMBER_RANGE = "CardNumber must be between 9 and 19 in length.";
    String YEAR_LENGTH = "Year must be two in length.";
    String YEAR_NOT_NUMERIC = "Year must be numeric.";
    String MONTH_LENGTH = "Month must be two in length.";
    String MONTH_NOT_NUMERIC = "Month must be numeric.";
    String SECURITY_CODE_LENGTH = "The securityCode must be three in length.";
    String TASK_ID = "taskId";
    String TRANSACTION_ID = "transactionId";
    String SEARCH = "search";
    String VALUE_BETWEEN = "The value of the %s must be between %s and %s.";
}
