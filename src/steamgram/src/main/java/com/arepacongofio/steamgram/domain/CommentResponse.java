package com.arepacongofio.steamgram.domain;

import jakarta.validation.constraints.NotBlank;

public class CommentResponse {
    
    @NotBlank
    private Integer id;
    @NotBlank
    private Integer idUser;
    @NotBlank
    private Integer idPost;
    @NotBlank
    private String text;

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

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
