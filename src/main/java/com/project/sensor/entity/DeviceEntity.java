package com.project.sensor.entity;

import javax.persistence.*;

@Entity
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private float temperature;
    private float gas;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public DeviceEntity() {}

    public DeviceEntity(long id, float temperature, float gas) {
        this.id = id;
        this.temperature = temperature;
        this.gas = gas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getGas() {
        return gas;
    }

    public void setGas(float gas) {
        this.gas = gas;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}