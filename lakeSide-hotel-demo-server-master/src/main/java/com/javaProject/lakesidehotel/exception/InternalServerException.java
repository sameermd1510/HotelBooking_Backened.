package com.javaProject.lakesidehotel.exception;

/**
 * @author Mohammad Sameer
 */

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
