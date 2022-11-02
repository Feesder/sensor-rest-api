package com.project.sensor.model;

import com.project.sensor.entity.DeviceEntity;

public class Device {
    private Long id;
    private Float temperature;

    private Float gas;
    private Float damp;
    private User user;

    public Device() {}

    public Device(DeviceEntity deviceEntity) {
        this.id = deviceEntity.getId();
        this.temperature = deviceEntity.getTemperature();
        this.gas = deviceEntity.getGas();
        this.damp = deviceEntity.getDamp();
        this.user = User.toModel(deviceEntity.getUser());
    }


    public static Device toModel(DeviceEntity deviceEntity) {
        return new Device(deviceEntity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getGas() {
        return gas;
    }

    public void setGas(Float gas) {
        this.gas = gas;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getDamp() {
        return damp;
    }

    public void setDamp(Float damp) {
        this.damp = damp;
    }
}
