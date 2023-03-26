package com.project.sensor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.sensor.entity.DeviceEntity;
import com.project.sensor.entity.ReportEntity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Report {
    private Long id;
    private Device device;
    private Integer temperature;
    private Integer gas;
    private Integer damp;
    private Timestamp date;

    public Report(Long id, Integer temperature, Integer gas, Integer damp, Timestamp date, Device device) {
        this.id = id;
        this.temperature = temperature;
        this.gas = gas;
        this.damp = damp;
        this.date = date;
        this.device = device;
    }

    public static Report toModel(ReportEntity report) {
        return new Report(
                report.getId(), report.getTemperature(),
                report.getGas(), report.getDamp(), report.getDate(), deviceToModel(report.getDevice())
        );
    }

    public static Device deviceToModel(DeviceEntity deviceEntity) {
        Device device = Device.toModel(deviceEntity);
        device.setReport(null);
        device.setUser(null);
        return device;
    }

    public static List<Report> toModel(Iterator<ReportEntity> reports) {
        ArrayList<Report> reportList = new ArrayList<>();
        while(reports.hasNext()) {
            reportList.add(Report.toModel(reports.next()));
        }
        reportList.sort((i, j) -> j.getDate().compareTo(i.getDate()));
        return reportList;
    }


    public static List<ReportEntity> toModel(List<ReportEntity> reportList) {
        reportList.sort((i, j) -> j.getDate().compareTo(i.getDate()));
        return reportList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getGas() {
        return gas;
    }

    public void setGas(Integer gas) {
        this.gas = gas;
    }

    public Integer getDamp() {
        return damp;
    }

    public void setDamp(Integer damp) {
        this.damp = damp;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
