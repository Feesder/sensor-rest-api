package com.project.sensor.service;

import com.project.sensor.entity.DeviceEntity;
import com.project.sensor.entity.UserEntity;
import com.project.sensor.model.Device;
import com.project.sensor.repository.DeviceRepository;
import com.project.sensor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    public Device addDevice(DeviceEntity deviceEntity, Long id) {
        UserEntity user = userRepository.findById(id).get();
        deviceEntity.setUser(user);
        return Device.toModel(deviceRepository.save(deviceEntity));
    }

    public Device getDevice(Long id) {
        return Device.toModel(deviceRepository.findById(id).get());
    }

    public Device updateDevice(Long id, DeviceEntity deviceEntity) {
        DeviceEntity device = deviceRepository.findById(id).get();
        device.setActive(deviceEntity.getActive());
        return Device.toModel(deviceRepository.save(device));
    }

    public Long deleteDevice(Long id) {
        deviceRepository.deleteById(id);
        return id;
    }
}
