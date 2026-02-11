package com.arepacongofio.steamgram.domain;

import jakarta.validation.constraints.NotBlank;

public class UserCreateRequest {
    
    @NotBlank
    String name;

    @NotBlank
    String user;

    @NotBlank
    String email;

    @NotBlank
    String password;

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
