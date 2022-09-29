package com.project.sensor.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String user;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<DeviceEntity> devices;

    public UserEntity() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
