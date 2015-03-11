package com.esaip.springboot.handball.security;

import com.esaip.springboot.handball.entities.User;
import com.esaip.springboot.handball.repositories.UserRepository;
import com.esaip.springboot.handball.services.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * This class wraps a regular Spring Security {@link UserDetailsService} implementation,
 * to retrieve a UserDetails object based on the username (email address) and password
 * contained in an Authentication object.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Service
@Transactional
public class LoggedUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggedUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SignService signService;

    @Autowired
    private HttpServletRequest request;

    /**
     * Loads the user information.
     *
     * @param username The username of the requested user (for us, it's the user email address).
     * @return The information of the user.
     * @throws UsernameNotFoundException Thrown if no user is found with the given username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Loading user by email address: {}", username);

        String ip = request.getRemoteAddr();
        if (signService.isBlocked(ip)) {
            throw new RuntimeException("Max login attempt for this IP");
        }

        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("The requested user is not found.");
        }

        return user;
    }

}
