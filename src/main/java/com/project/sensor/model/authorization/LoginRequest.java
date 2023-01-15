package com.project.sensor.model.authorization;

public class LoginRequest {
    private String user;
    private String password;

    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.user = username;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String username) {
        this.user = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
