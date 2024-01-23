package com.javaProject.lakesidehotel.exception;

/**
 * @author Mohammad Sameer
 */

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
