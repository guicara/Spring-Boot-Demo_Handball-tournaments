package com.esaip.springboot.handball.services.exceptions;

/**
 * This exception is thrown if the wanted result is not found
 *
 * @author Guillaume MOREL-BAILLY
 */
public class ResultNotFoundException extends RuntimeException {

    public ResultNotFoundException(final String message) {
        super(message);
    }

}