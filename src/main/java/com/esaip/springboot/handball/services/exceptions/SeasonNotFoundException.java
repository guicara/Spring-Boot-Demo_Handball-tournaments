package com.esaip.springboot.handball.services.exceptions;

/**
 * This exception is thrown if the wanted season is not found
 *
 * @author Guillaume MOREL-BAILLY
 */
public class SeasonNotFoundException extends RuntimeException {

    public SeasonNotFoundException(final String message) {
        super(message);
    }

}