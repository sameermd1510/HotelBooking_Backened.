package com.javaProject.lakesidehotel.exception;

/**
 * @author Mohammad Sameer
 */

public class InvalidBookingRequestException extends RuntimeException {
    public InvalidBookingRequestException(String message) {
        super(message);
    }
}
