package com.project.sensor.model;

import com.project.sensor.entity.DeviceEntity;

public class Device {
    private Long id;
    private Boolean isActive;
    private User user;

    public Device() {}

    public Device(Long id, Boolean isActive, User user) {
        this.id = id;
        this.isActive = isActive;
        this.user = user;
    }

    public static Device toModel(DeviceEntity deviceEntity) {
        return new Device(
                deviceEntity.getId(),
                deviceEntity.getActive(),
                User.toModel(deviceEntity.getUser())
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
