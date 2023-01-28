package com.project.sensor.repository;

import com.project.sensor.entity.PlaceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<PlaceEntity, Long> {
}
