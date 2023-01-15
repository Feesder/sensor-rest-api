package com.project.sensor.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "reports")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id")
    private DeviceEntity device;

    @Column(name = "temperature")
    private Integer temperature;

    @Column(name = "gas")
    private Integer gas;

    @Column(name = "damp")
    private Integer damp;

    @Column(name = "date")
    private Timestamp date;

    public ReportEntity(Integer temperature, Integer gas, Integer damp) {
        this.temperature = temperature;
        this.gas = gas;
        this.damp = damp;
    }

    public ReportEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
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