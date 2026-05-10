package com.arepacongofio.steamgram.domain.requests;

import jakarta.validation.constraints.NotBlank;

public class GameSearchRequest {
    @NotBlank
    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
