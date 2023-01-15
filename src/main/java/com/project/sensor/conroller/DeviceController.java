package com.project.sensor.conroller;

import com.project.sensor.entity.DeviceEntity;
import com.project.sensor.repository.DeviceRepository;
import com.project.sensor.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity addDevice(@RequestBody DeviceEntity entity, @RequestParam Long userId) {
        try {
            return ResponseEntity.ok().body(deviceService.addDevice(entity, userId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getDevice(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(deviceService.getDevice(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Такое устройство не существует");
        }
    }

    @PutMapping
    public ResponseEntity putDevice(@RequestParam Long id, @RequestBody DeviceEntity deviceEntity) {
        try {
            return ResponseEntity.ok().body(deviceService.updateDevice(id, deviceEntity));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteDevice(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(deviceService.deleteDevice(id));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
