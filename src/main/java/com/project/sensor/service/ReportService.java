package com.project.sensor.service;

import com.project.sensor.entity.DeviceEntity;
import com.project.sensor.entity.ReportEntity;
import com.project.sensor.exception.DeviceNotFoundException;
import com.project.sensor.model.Device;
import com.project.sensor.model.Report;
import com.project.sensor.repository.DeviceRepository;
import com.project.sensor.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public ReportEntity addReport(ReportEntity reportEntity, Long deviceId) throws DeviceNotFoundException {
        DeviceEntity deviceEntity = deviceRepository.findById(deviceId).get();
        if(deviceEntity.getId() == null) {
            throw new DeviceNotFoundException("Устройство не найдено");
        }
        reportEntity.setDevice(deviceEntity);
        reportEntity.setDate(new Timestamp(System.currentTimeMillis()));
        return reportRepository.save(reportEntity);
    }

    public List<ReportEntity> getReportAll() {
        return Report.toModel(reportRepository.findAll().iterator());
    }

    public List<ReportEntity> getReportById(Long id) {
        return Report.toModel(reportRepository.findReportEntitiesByDeviceId(id));
    }

    public Long deleteReport(Long id) {
        reportRepository.deleteById(id);
        return id;
    }
}
