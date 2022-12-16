package com.project.sensor.service;

import com.project.sensor.entity.DeviceEntity;
import com.project.sensor.entity.UserEntity;
import com.project.sensor.model.Device;
import com.project.sensor.repository.DeviceRepository;
import com.project.sensor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    public DeviceEntity addDevice(DeviceEntity deviceEntity, Long id) {
        UserEntity user = userRepository.findById(id).get();
        deviceEntity.setUser(user);
        return deviceRepository.save(deviceEntity);
    }

    public Device getDevice(Long id) {
        return Device.toModel(deviceRepository.findById(id).get());
    }

    public Device putDevice(Long id, Float temperature, Float gas, Float damp) {
        DeviceEntity device = deviceRepository.findById(id).get();
        device.setGas(gas);
        device.setTemperature(temperature);
        device.setDamp(damp);
        return Device.toModel(deviceRepository.save(device));
    }

    public Device putDevice(Long id, DeviceEntity deviceEntity) {
        DeviceEntity device = deviceRepository.findById(id).get();
        device.setGas(deviceEntity.getGas());
        device.setTemperature(deviceEntity.getTemperature());
        return Device.toModel(deviceRepository.save(device));
    }
}
