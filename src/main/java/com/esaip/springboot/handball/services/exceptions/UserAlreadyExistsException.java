package com.esaip.springboot.handball.services.exceptions;

/**
 * This exception is thrown if the user/email address already exists
 *
 * @author Guillaume MOREL-BAILLY
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String message) {
        super(message);
    }

}