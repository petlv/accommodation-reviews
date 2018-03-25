package org.softuni.accommodationreviews.models.binding;

import javax.validation.constraints.Size;

public class UserBindingModel {

    @Size(min = 4, max = 35, message = "Length must be between 4 and 35 symbols")
    private String username;

    @Size(min = 4, max = 35, message = "Length must be between 4 and 35 symbols")
    private String password;

    private String confirmPassword;

    @Size(min = 4, max = 35, message = "Length must be between 4 and 35 symbols")
    private String fullName;

    @Size(min = 4, max = 35, message = "Length must be between 4 and 35 symbols")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
