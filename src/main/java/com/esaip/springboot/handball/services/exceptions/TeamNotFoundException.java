package com.esaip.springboot.handball.services.exceptions;

/**
 * This exception is thrown if the wanted team is not found
 *
 * @author Guillaume MOREL-BAILLY
 */
public class TeamNotFoundException extends RuntimeException {

    public TeamNotFoundException(final String message) {
        super(message);
    }

}