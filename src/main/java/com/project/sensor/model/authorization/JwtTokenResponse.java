package com.project.sensor.model.authorization;

import com.project.sensor.entity.UserEntity;
import com.project.sensor.model.User;

import java.util.List;

public class JwtTokenResponse {
    private String accessToken;
    private String refreshToken;

    private User user;

    public JwtTokenResponse(User user, String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
