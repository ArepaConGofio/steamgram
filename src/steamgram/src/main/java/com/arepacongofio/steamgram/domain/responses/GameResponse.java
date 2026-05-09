package com.arepacongofio.steamgram.domain.responses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GameResponse {

    @NotNull
    Integer idGame;

    String idSteam;

    @NotNull
    Integer idDeveloper;

    String publisher;

    @NotBlank
    String name;

    String banner;

    @NotBlank
    String description;
    
    String genre;
    
    public Integer getIdGame() {
        return idGame;
    }
    
    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }
    
    public String getIdSteam() {
        return idSteam;
    }
    
    public void setIdSteam(String idSteam) {
        this.idSteam = idSteam;
    }
    
    public Integer getIdDeveloper() {
        return idDeveloper;
    }
    
    public void setIdDeveloper(Integer idDeveloper) {
        this.idDeveloper = idDeveloper;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
        public String getBanner() {
            return banner;
        }
    
        public void setBanner(String banner) {
            this.banner = banner;
        }
    
        public String getDescription() {
            return description;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }

}
