package com.alten.shop.utils.exceptions.Uncheck.user;

public class UserAlreadyExistsException extends RuntimeException {
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}