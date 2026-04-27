package com.arepacongofio.steamgram.domain.requests;

import jakarta.validation.constraints.NotBlank;

public class LikeCreateRequest {

    @NotBlank
    Integer idUser;
    @NotBlank
    Integer idPost;

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

}
