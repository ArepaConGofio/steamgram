package com.arepacongofio.steamgram.domain.responses;

public class LikePostResponse {
    Integer idPost;
    Integer idUser;
    boolean isLiked;

    public LikePostResponse(Integer idPost, Integer idUser, boolean isLiked) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.isLiked = isLiked;
    }
    
    public Integer getIdPost() {
        return idPost;
    }
    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }
    public Integer getIdUser() {
        return idUser;
    }
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    public boolean isLiked() {
        return isLiked;
    }
    public void setLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }
}
