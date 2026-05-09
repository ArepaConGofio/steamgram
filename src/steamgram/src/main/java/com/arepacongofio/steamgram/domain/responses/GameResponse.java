package com.arepacongofio.steamgram.domain.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GameResponse {

    Integer idGame;

    @NotNull
    Integer idIgdb;

    @NotNull
    Integer idDeveloper;

    @NotBlank
    String developerName;

    @NotBlank
    String title;

    String banner;

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public Integer getIdIgdb() {
        return idIgdb;
    }

    public void setIdIgdb(Integer idIgdb) {
        this.idIgdb = idIgdb;
    }

    public Integer getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(Integer idDeveloper) {
        this.idDeveloper = idDeveloper;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

}
