package pl.ania.security;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserModel {


    private static final String MESSAGE = "wymagane są minimum 3 znaki";

    @NotEmpty(message = "pole nie może być puste")
    @Length(min = 3, message = MESSAGE)
    private String username;

    @Length(min = 3, message = MESSAGE)
    private String password;

    @Length(min = 3, message = MESSAGE)
    private String confPassword;

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

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
