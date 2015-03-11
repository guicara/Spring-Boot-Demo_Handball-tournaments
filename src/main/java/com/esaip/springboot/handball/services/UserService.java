package com.esaip.springboot.handball.services;

import com.esaip.springboot.handball.dto.UserCreateDTO;
import com.esaip.springboot.handball.dto.UserEditDTO;
import com.esaip.springboot.handball.entities.User;
import com.esaip.springboot.handball.services.exceptions.UserNotFoundException;

import java.util.List;

/**
 * Declares methods used to obtain and modify user information
 *
 * @author Guillaume MOREL-BAILLY
 */
public interface UserService {

    /**
     * Finds all users
     *
     * @return A list of users
     */
    public List<User> getAll();

    /**
     * Finds user by ID
     *
     * @param id The ID of the wanted user
     * @return The found user. If no user is found, this method returns null.
     */
    public User get(Long id);

    /**
     * Creates a new user
     *
     * @param userForm The information of the created user
     * @return The created user
     */
    public User create(UserCreateDTO userForm);

    /**
     * Updates the information of a user
     *
     * @param userForm The information of the updated user
     * @return The updated user
     * @throws UserNotFoundException If no user is found with given ID
     */
    public User update(UserEditDTO userForm) throws UserNotFoundException;

    /**
     * Deletes a user
     *
     * @param id The ID of the deleted user
     * @throws UserNotFoundException If no user is found with the given ID
     */
    public void delete(Long id) throws UserNotFoundException;

}
