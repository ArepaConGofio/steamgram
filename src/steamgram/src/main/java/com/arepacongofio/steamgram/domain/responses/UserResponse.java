package com.arepacongofio.steamgram.domain.responses;

public class UserResponse {
    
    Integer id;
    String username;
    String email;
    String avatarUrl;
    String nickname;
    int gamesCount;
    int followersCount;
    int followingCount;
    int reviewsCount;
    int postsCount;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getGamesCount() {
        return gamesCount;
    }
    public void setGamesCount(int gamesCount) {
        this.gamesCount = gamesCount;
    }
    public int getFollowersCount() {
        return followersCount;
    }
    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }
    public int getFollowingCount() {
        return followingCount;
    }
    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }
    public int getReviewsCount() {
        return reviewsCount;
    }
    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }
    public int getPostsCount() {
        return postsCount;
    }
    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    
}
