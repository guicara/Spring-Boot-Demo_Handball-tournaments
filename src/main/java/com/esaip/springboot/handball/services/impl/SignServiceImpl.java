package com.esaip.springboot.handball.services.impl;

import com.esaip.springboot.handball.dto.RegistrationDTO;
import com.esaip.springboot.handball.dto.UserCreateDTO;
import com.esaip.springboot.handball.entities.User;
import com.esaip.springboot.handball.events.OnRegistrationEvent;
import com.esaip.springboot.handball.repositories.UserRepository;
import com.esaip.springboot.handball.services.SignService;
import com.esaip.springboot.handball.services.exceptions.UserAlreadyExistsException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Sign in / Sign up Service
 *
 * This implementation of the SignService interface communicates with
 * the database by using a Spring Data JPA repository.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Service
public class SignServiceImpl implements SignService {

    private final int MAX_LOGIN_ATTEMPT = 5;
    private LoadingCache<String, Integer> attemptsCache;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public SignServiceImpl() {
        super();
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    @Transactional
    public User register(@NotNull @Valid final RegistrationDTO registrationForm) {
        User existing = userRepository.findByEmail(registrationForm.getEmail());
        if (existing != null) {
            throw new UserAlreadyExistsException(String.format("There already exists a user with email = %s", registrationForm.getEmail()));
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(registrationForm.getPassword());

        User user = new User(registrationForm.getEmail(), password, registrationForm.getFirstName(), registrationForm.getLastName(), "USER");

        User registered = userRepository.save(user);
        if (registered != null) {
            // Sending an email to confirm the registration
            eventPublisher.publishEvent(new OnRegistrationEvent(registered));
        }

        return registered;
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(String key) {
        try {
            return attemptsCache.get(key) >= MAX_LOGIN_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }

}
