package com.project.sensor.model;

import com.project.sensor.entity.UserEntity;

public class User {
    private Long id;
    private String user;

    public User() {}
    public User(Long id, String user) {
        this.id = id;
        this.user = user;
    }

    public static User toModel(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
