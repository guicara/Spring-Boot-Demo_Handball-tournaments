package com.esaip.springboot.handball.services;

import com.esaip.springboot.handball.dto.RegistrationDTO;
import com.esaip.springboot.handball.entities.User;

/**
 * Declares methods used to register/sign up users
 *
 * @author Guillaume MOREL-BAILLY
 */
public interface SignService {

    /**
     * Registers a new user
     *
     * @param registrationForm The information of the new user
     * @return The new user
     */
    public User register(RegistrationDTO registrationForm);

    // Prevent Brute Force authentication attempts
    public void loginSucceeded(String key);
    public void loginFailed(String key);
    public boolean isBlocked(String key);

}
