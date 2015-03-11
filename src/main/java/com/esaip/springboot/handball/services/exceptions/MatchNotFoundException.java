package com.esaip.springboot.handball.services.exceptions;

/**
 * This exception is thrown if the wanted match is not found
 *
 * @author Guillaume MOREL-BAILLY
 */
public class MatchNotFoundException extends RuntimeException {

    public MatchNotFoundException(final String message) {
        super(message);
    }

}