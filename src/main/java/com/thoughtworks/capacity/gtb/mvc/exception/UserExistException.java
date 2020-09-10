package com.thoughtworks.capacity.gtb.mvc.exception;

public class UserExistException extends RuntimeException{
    public UserExistException(String message) {
        super(message);
    }
}
