package com.arepacongofio.steamgram.domain.requests;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class DeveloperRequest {

    @NotBlank
    String name;

    List<Integer> usersId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Integer> usersId) {
        this.usersId = usersId;
    }

}
