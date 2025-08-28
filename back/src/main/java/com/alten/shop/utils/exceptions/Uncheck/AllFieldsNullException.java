package com.alten.shop.utils.exceptions.Uncheck;

public class AllFieldsNullException extends RuntimeException {
    public AllFieldsNullException(String message) {
        super(message);
    }

    public AllFieldsNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
