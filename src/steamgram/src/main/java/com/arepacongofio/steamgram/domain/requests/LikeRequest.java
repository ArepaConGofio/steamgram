package com.arepacongofio.steamgram.domain.requests;

import jakarta.validation.constraints.NotNull;

public class LikeRequest {

    @NotNull
    Integer idUser;

    @NotNull
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
