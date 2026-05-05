package com.arepacongofio.steamgram.domain.requests;

public class UserFindRequest {
    String name;
    String nickname;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
