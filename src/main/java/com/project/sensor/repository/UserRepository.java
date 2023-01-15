package com.project.sensor.repository;

import com.project.sensor.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUser(String user);
    Boolean existsByUser(String user);
    Boolean existsByEmail(String email);
}