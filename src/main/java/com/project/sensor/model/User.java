package com.project.sensor.model;

import com.project.sensor.entity.DeviceEntity;
import com.project.sensor.entity.RoleEntity;
import com.project.sensor.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Long id;
    private String user;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private List<String> roles;
    private List<Device> devices;

    public User(Long id, String user, String email, String name, String surname, String phone, List<Device> devices, List<String> roles) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
        this.phone = phone;
        this.devices = devices;
    }

    public User(Long id, String user, String email, String name, String surname) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public static User toModel(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUser(),
                userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getPhone(),
                mapToDevices(userEntity.getDevices()),
                mapToGrantedAuthorities(userEntity.getRoles())
        );
    }

    public static List<String> mapToGrantedAuthorities(List<RoleEntity> userRoles) {
        return userRoles.stream().map(RoleEntity::getName).collect(Collectors.toList());
    }

    public static List<Device> mapToDevices(List<DeviceEntity> deviceEntities) {
        return deviceEntities.stream().map(Device::toModel).collect(Collectors.toList());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}