package com.fdc.mtrg.network.token.error;

public interface FdcServiceError {
    String getStatusCode();

    String getErrorCode();

    String getErrorDescription();

    String getCategory();

    String getDeveloperMessage();

    String getMoreInfo();
}