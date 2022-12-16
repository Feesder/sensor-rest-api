package com.project.sensor.conroller;

import com.project.sensor.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setDevice")
public class DeviceSetController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public ResponseEntity setDevice(@RequestParam Long id, @RequestParam Float temperature, @RequestParam Float gas, @RequestParam Float damp) {
        try {
            return ResponseEntity.ok().body(deviceService.putDevice(id, temperature, gas, damp));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
