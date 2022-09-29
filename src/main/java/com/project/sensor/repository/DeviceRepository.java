package com.project.sensor.repository;

import com.project.sensor.entity.DeviceEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {}