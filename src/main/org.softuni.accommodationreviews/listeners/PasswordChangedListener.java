package org.softuni.accommodationreviews.listeners;

import org.softuni.accommodationreviews.areas.users.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PasswordChangedListener {

    @PrePersist
    @PreUpdate
    public void onPasswordChange(User user) {
        user.setPassword(
                new BCryptPasswordEncoder().encode(user.getPassword())
        );
    }
}
