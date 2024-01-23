package com.javaProject.lakesidehotel.exception;

/**
 * @author Mohammad Sameer
 */

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
