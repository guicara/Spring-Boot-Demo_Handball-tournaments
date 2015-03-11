package com.esaip.springboot.handball.security;

import com.esaip.springboot.handball.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * User authentication with Spring MVC Security
 *
 * EnableWebMvcSecurity basically pulls in the default SpringSecurity/SpringMVC integration.
 * It’s an extension of the WebMvcConfigurerAdapter, and adds methods for:
 * - handling and generating CSRF tokens
 * - resolving the logged in user
 * - configure default AuthenticationManagers
 * - configure Pre/Post object authorization implementations
 *
 * @author Guillaume MOREL-BAILLY
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Configures URL based authorization
                .authorizeRequests()
                    .antMatchers("/", "/about", "/register", "/webjars/**", "/css/**", "/js/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                // Configures form login
                .formLogin()
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/dashboard")
                    .loginPage("/login")
                    .permitAll()
                    .and()
                // Configures the logout function
                .logout()
                    .deleteCookies("JSESSIONID")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                    .permitAll();
    }

    /**
     * Configures the authentication manager bean which processes authentication
     * requests.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    /**
     * This is used to hash the password of the user.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    /**
     * This bean is load the user specific data when form login is used.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new LoggedUserDetailsService();
    }

}
