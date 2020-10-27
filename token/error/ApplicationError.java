package com.fdc.mtrg.network.token.error;


public enum ApplicationError implements FdcServiceError {
    SERVICE_UNAVAILABLE("500", "500000", "This service is down at the moment, please try again later", "common", "This service is down at the moment, please try again later"),
    SERVER_ERROR("500", "500001", "Unable to process your request, Please try again later, if the problem persist, contact system admin.", "common", "Unable to process your request, Please try again later, if the problem persists, contact system admin."),
    IO_EXCEPTION("500", "500002", "Unable to process your request, Please try again later, if the problem persist, contact system admin.", "common", "Unable to process your request, Please try again later, if the problem persists, contact system admin."),
    INVALID_REQUEST("400", "400000", "Invalid request format/data.", "common", "Invalid request format/data."),
    TOKENIZATION_FAILED("400", "400001", "Tokenization is unsuccessful. Please check the input details.", "provission tokenization", "Tokenization is unsuccessful. Please check the input details."),
    TRANSACT_FAILED("400", "400011", "Transaction is unsuccessful.", "Transaction", "Transaction is unsuccessful. Please check the card details."),
    AUTHORIZATION_FAILED("400", "400002", "Network authorization failed.", "common", "Network authorization failed."),
    CARD_NOT_FOUND("400", "400003", "Network couldn't find that card number.", "provission tokenization", "Network couldn't find that card number."),
    CARD_BLOCKED("400", "400004", "Card used for tokenization is blocked.", "provission tokenization", "Card used for tokenization is blocked."),
    BLOCKED("400", "400005", "Blocked for suspicious activity.", "common", "Blocked for suspicious activity."),
    TRANSACTION_NOT_FOUND("400", "400007", "Error: transaction not found.", "provission tokenization", "Error: transaction not found."),
    EMAIL_NOT_VALID("400", "400008", "Email is already used, invalid or blocked.", "provission tokenization", "Email is already used, invalid or blocked."),
    SESSION_EXPIRED("400", "400009", "Your secure session has expired due to a longer period of inactivity. Please try again.", "Account", "Your secure session has expired due to a longer period of inactivity. Please try again."),
    SERVICE_TIMEOUT("400", "400010", "Service has a timeout and will be back shortly.", "provission tokenization", "Service has a timeout and will be back shortly.");


    private String statusCode;
    private String errorCode;
    private String errorDescription;
    private String category;
    private String developerMessage;

    private ApplicationError(String statusCode, String errorCode, String errorDescription, String category, String developerMessage) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.category = category;
        this.developerMessage = developerMessage;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public String getCategory() {
        return this.category;
    }

    public String getDeveloperMessage() {
        return this.developerMessage;
    }

    public static ApplicationError getByErrorCode(String errorCode) {
        ApplicationError errorMapper = null;
        if (null != errorCode) {
            ApplicationError[] var2 = values();
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                ApplicationError each = var2[var4];
                if (each.getErrorCode().equals(errorCode)) {
                    errorMapper = each;
                    break;
                }
            }
        }

        return errorMapper;
    }

    public static ApplicationError getByErrorName(String name) {
        ApplicationError errorMapper = null;
        if (null != name) {
            ApplicationError[] var2 = values();
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                ApplicationError each = var2[var4];
                if (each.name().equals(name)) {
                    errorMapper = each;
                    break;
                }
            }
        }

        return errorMapper;
    }

    public String getMoreInfo() {
        return null;
    }
    }

