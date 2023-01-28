package com.project.sensor.model;

import com.project.sensor.entity.DeviceEntity;
import com.project.sensor.entity.ReportEntity;
import com.project.sensor.entity.RoleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Device {
    private Long id;
    private Boolean isActive;
    private Place place;
    private User user;
    private List<Report> report;

    public Device() {}

    public Device(Long id, Boolean isActive, User user, Place place, List<Report> report) {
        this.id = id;
        this.isActive = isActive;
        this.user = user;
        this.place = place;
        this.report = report;
    }

    public static Device toModel(DeviceEntity deviceEntity) {
        return new Device(
                deviceEntity.getId(),
                deviceEntity.getActive(),
                User.toModel(deviceEntity.getUser()),
                Place.toModel(deviceEntity.getPlace()),
                mapToReport(deviceEntity.getReports())
        );
    }

    public static List<Report> mapToReport(List<ReportEntity> reports) {
        return reports.stream().map(Report::toModel).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Report> getReport() {
        return report;
    }

    public void setReport(List<Report> report) {
        this.report = report;
    }
}
