package com.javaProject.lakesidehotel.exception;

/**
 * @author Mohammad Sameer
 */

public class RoleAlreadyExistException extends RuntimeException {
    public RoleAlreadyExistException(String message) {
        super(message);
    }
}
