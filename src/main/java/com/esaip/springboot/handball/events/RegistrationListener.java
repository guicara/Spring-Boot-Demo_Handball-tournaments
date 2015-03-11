package com.esaip.springboot.handball.events;

import com.esaip.springboot.handball.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * An application listener class which throw an event on registration success
 *
 * @author Guillaume MOREL-BAILLY
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationEvent> {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MessageSource messages;

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(OnRegistrationEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationEvent event) {
        User user = event.getUser();
        final SimpleMailMessage email = constructEmailMessage(event, user);
        mailSender.send(email);
    }

    private final SimpleMailMessage constructEmailMessage(final OnRegistrationEvent event, final User user) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String message = messages.getMessage("contact.msgRegistration", null, Locale.FRANCE);
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom(env.getProperty("contact.email"));
        return email;
    }

}
