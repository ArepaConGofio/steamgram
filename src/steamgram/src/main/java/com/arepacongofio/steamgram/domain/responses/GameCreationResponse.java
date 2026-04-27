package com.arepacongofio.steamgram.domain.responses;

import jakarta.validation.constraints.NotBlank;

public class GameCreationResponse {

    @NotBlank
    Integer id;

    @NotBlank
    String name;

    @NotBlank
    Integer idDeveloper;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(Integer idDeveloper) {
        this.idDeveloper = idDeveloper;
    }

}
