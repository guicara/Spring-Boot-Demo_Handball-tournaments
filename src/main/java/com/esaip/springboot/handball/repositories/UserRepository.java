package com.esaip.springboot.handball.repositories;

import com.esaip.springboot.handball.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA-powered <em>repository</em> interface.
 * Supports common operations like {@link #findAll()} and {@link #save(Object)} against JPA entities.
 * This particular repository deals in {@link com.esaip.springboot.handball.entities.User user} objects.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);

}
