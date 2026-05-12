package com.arepacongofio.steamgram.domain.responses;

public class LikePostResponse {
    Integer idPost;
    Integer idUser;
    boolean isLiked;
    Integer likes;

    public LikePostResponse(Integer idPost, Integer idUser, boolean isLiked, Integer likes) {
        this.idPost = idPost;
        this.idUser = idUser;
        this.isLiked = isLiked;
        this.likes = likes;
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

}
