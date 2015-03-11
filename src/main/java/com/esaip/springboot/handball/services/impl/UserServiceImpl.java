package com.esaip.springboot.handball.services.impl;

import com.esaip.springboot.handball.dto.UserCreateDTO;
import com.esaip.springboot.handball.dto.UserEditDTO;
import com.esaip.springboot.handball.entities.User;
import com.esaip.springboot.handball.repositories.UserRepository;
import com.esaip.springboot.handball.services.UserService;
import com.esaip.springboot.handball.services.exceptions.UserAlreadyExistsException;
import com.esaip.springboot.handball.services.exceptions.UserNotFoundException;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User Service
 *
 * This implementation of the UserService interface communicates with
 * the database by using a Spring Data JPA repository.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Finds all users
     *
     * @return A list of users
     */
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Finds user by ID
     *
     * @param id The ID of the wanted user
     * @return The found user. If no user is found, this method returns null.
     */
    @Transactional(readOnly = true)
    public User get(Long id) {
        return userRepository.findOne(id);
    }

    /**
     * Creates a new user
     *
     * @param userForm The information of the created user
     * @return The created user
     */
    @Transactional(readOnly = false)
    public User create(UserCreateDTO userForm) {
        User existing = userRepository.findByEmail(userForm.getEmail());
        if (existing != null) {
            throw new UserAlreadyExistsException(String.format("There already exists a user with email = %s", userForm.getEmail()));
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(userForm.getPassword());

        User user = new User(userForm.getEmail(), password, userForm.getFirstName(), userForm.getLastName(), userForm.getRole());

        return userRepository.save(user);
    }

    /**
     * Updates the information of a user
     *
     * @param userForm The information of the updated user
     * @return The updated user
     * @throws UserNotFoundException If no user is found with given ID
     */
    @Transactional(readOnly = false, rollbackFor = UserNotFoundException.class)
    public User update(UserEditDTO userForm) throws UserNotFoundException {
        User user = userRepository.findOne(userForm.getId());

        if (user == null) {
            throw new UserNotFoundException("No user found with id = " + userForm.getId());
        }

        user.setEmail(userForm.getEmail());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setRole(userForm.getRole());

        if (!Strings.isNullOrEmpty(userForm.getPassword())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(userForm.getPassword());
            user.setPassword(password);
        }

        return userRepository.save(user);
    }

    /**
     * Deletes a user
     *
     * @param id The ID of the deleted user
     * @throws UserNotFoundException If no user is found with the given ID
     */
    @Transactional(readOnly = false, rollbackFor = UserNotFoundException.class)
    public void delete(Long id) throws UserNotFoundException {
        User user = userRepository.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("No user found with id = " + id);
        }

        // Prevent the user from deleting his own account
        User logged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (logged != null && logged.getId() != id) {
            userRepository.delete(id);
        }
    }

}
