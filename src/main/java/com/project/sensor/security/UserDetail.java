package com.project.sensor.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.sensor.entity.RoleEntity;
import com.project.sensor.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetail implements UserDetails {

    private Long id;
    private String user;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phone;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetail(Long id, String user, String email, String password, String name, String surname, String phone, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.authorities = authorities;
    }

    public static UserDetail toModel(UserEntity userEntity) {
        return new UserDetail(
                userEntity.getId(),
                userEntity.getUser(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getSurname(),
                userEntity.getPhone(),
                mapToGrantedAuthorities(userEntity.getRoles())
        );
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleEntity> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return user;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}