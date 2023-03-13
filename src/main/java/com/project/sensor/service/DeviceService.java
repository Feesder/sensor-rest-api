package com.project.sensor.service;

import com.project.sensor.entity.DeviceEntity;
import com.project.sensor.entity.PlaceEntity;
import com.project.sensor.entity.UserEntity;
import com.project.sensor.model.Device;
import com.project.sensor.repository.DeviceRepository;
import com.project.sensor.repository.PlaceRepository;
import com.project.sensor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private UserRepository userRepository;

    public Device addDevice(DeviceEntity deviceEntity, Long userId, Long placeId) {
        UserEntity user = userRepository.findById(userId).get();
        PlaceEntity place = placeRepository.findById(placeId).get();
        deviceEntity.setUser(user);
        deviceEntity.setPlace(place);
        return Device.toModel(deviceRepository.save(deviceEntity));
    }

    public Device getDevice(Long id) {
        return Device.toModel(deviceRepository.findById(id).get());
    }

    public List<Device> getAllDevice() {
        ArrayList<Device> devices = new ArrayList<>();
        deviceRepository.findAll().forEach(value -> {
            System.out.println(value);
           devices.add(Device.toModel(value));
        });
        return devices;
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
