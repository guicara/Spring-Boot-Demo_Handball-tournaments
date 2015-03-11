package com.esaip.springboot.handball.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.security.Principal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;

/**
 * An entity class which contains the information of a single user.
 *
 * @author Guillaume MOREL-BAILLY
 */
@Entity
@Table(name = "users")
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", length = 100, nullable = true)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = true)
    private String lastName;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "role", nullable = true)
    private String role;

    @Column(name = "created_at", nullable = true)
    private Date createdAt;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    public User() {
    }

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String password, String firstName, String lastName, String role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getRoleTxt() {
        if (role != null) {
            return role.equals("ADMIN") ? "Administrateur" : "Utilisateur";
        }
        return "";
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getName() {
        StringBuilder name = new StringBuilder();

        name.append(firstName);
        name.append(" ");
        name.append(lastName);

        return name.toString();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
        if (getId() != null && !"user".equalsIgnoreCase(getRole())) {
            collection.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        if (getRole() != null) {
            collection.add(new SimpleGrantedAuthority("ROLE_" + getRole()));
        }
        return collection;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "User [id=" + id +", email=" + email
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", password=" + password
                + ", role=" + role
                + "]";
    }

}
