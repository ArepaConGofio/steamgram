package com.arepacongofio.steamgram.domain;

import jakarta.validation.constraints.NotBlank;

public class ReviewResponse {

    @NotBlank
    Integer id;

    @NotBlank
    Integer idGame;

    @NotBlank
    Integer idUSer;

    @NotBlank
    String title;

    Integer description;

    Integer rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public Integer getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(Integer idUSer) {
        this.idUSer = idUSer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    
}
