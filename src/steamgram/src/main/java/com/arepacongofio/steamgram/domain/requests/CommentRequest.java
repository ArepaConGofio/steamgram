package com.arepacongofio.steamgram.domain.requests;

import jakarta.validation.constraints.NotBlank;

public class CommentRequest {

    @NotBlank
    Integer idUser;
    @NotBlank
    Integer idPost;

    String text;

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
