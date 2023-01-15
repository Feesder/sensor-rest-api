package com.project.sensor.repository;

import com.project.sensor.entity.DeviceEntity;
import com.project.sensor.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {
    DeviceEntity findByUserId(Long userId);
}