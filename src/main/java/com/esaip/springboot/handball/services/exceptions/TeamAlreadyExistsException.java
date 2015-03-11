package com.esaip.springboot.handball.services.exceptions;

/**
 * This exception is thrown if the team already exists (same name)
 *
 * @author Guillaume MOREL-BAILLY
 */
public class TeamAlreadyExistsException extends RuntimeException {

    public TeamAlreadyExistsException(final String message) {
        super(message);
    }

}