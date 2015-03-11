package com.esaip.springboot.handball.events;

import com.esaip.springboot.handball.entities.User;
import org.springframework.context.ApplicationEvent;

/**
 * Event OnRegistration
 *
 * @author Guillaume MOREL-BAILLY
 */
public class OnRegistrationEvent extends ApplicationEvent {

    private final User user;

    public OnRegistrationEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
