package com.esaip.springboot.handball.services.exceptions;

/**
 * This exception is thrown if the wanted user is not found
 *
 * @author Guillaume MOREL-BAILLY
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final String message) {
        super(message);
    }

}