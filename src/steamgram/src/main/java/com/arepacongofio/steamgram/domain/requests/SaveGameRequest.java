package com.arepacongofio.steamgram.domain.requests;

public class SaveGameRequest {
    Integer gameId;
    Integer userId;

    public Integer getGameId() {
        return gameId;
    }
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }  
}
