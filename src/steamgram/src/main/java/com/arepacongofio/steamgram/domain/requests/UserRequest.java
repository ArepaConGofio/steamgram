package com.arepacongofio.steamgram.domain.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {

    String name;

    @NotBlank
    String nickname;

    @NotBlank
    @Email
    String email;

    @NotBlank
    String password;

    public UserRequest() {
    }

    public UserRequest(String name, @NotBlank String nickname, @NotBlank @Email String email,
            @NotBlank String password) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
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

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
