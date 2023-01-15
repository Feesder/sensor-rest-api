package com.project.sensor.repository;

import com.project.sensor.entity.ReportEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReportRepository extends CrudRepository<ReportEntity, Long> {
    List<ReportEntity> findReportEntitiesByDeviceId(Long deviceId);
}