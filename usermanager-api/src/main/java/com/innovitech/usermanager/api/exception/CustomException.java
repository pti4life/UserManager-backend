package com.innovitech.usermanager.api.exception;

public class CustomException extends RuntimeException {

    private final String message;

    private final int HttpStatusCode;


    public CustomException(String message, int HtppStatusCode) {
        this.message = message;
        this.HttpStatusCode = HtppStatusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getHttpStatusCode() {
        return HttpStatusCode;
    }
}
