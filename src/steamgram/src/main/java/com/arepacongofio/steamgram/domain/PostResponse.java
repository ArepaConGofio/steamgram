package com.arepacongofio.steamgram.domain;

import jakarta.validation.constraints.NotBlank;

public class PostResponse {
    
    @NotBlank
    Integer id;
    
    @NotBlank
    Integer idUser;
    
    @NotBlank
    Integer idGame;
    
    @NotBlank
    String title;
    
    String description;
    
    String attachment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdGame() {
        return idGame;
    }

    public void setIdGame(Integer idGame) {
        this.idGame = idGame;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    
}
