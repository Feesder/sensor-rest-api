package com.project.sensor.conroller;

import com.project.sensor.entity.ReportEntity;
import com.project.sensor.exception.DeviceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.sensor.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity addReport(@RequestBody ReportEntity reportEntity, @RequestParam Long deviceId) {
        try {
            return ResponseEntity.ok().body(reportService.addReport(reportEntity, deviceId));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping(params = { "deviceId", "temperature", "gas", "damp", "date" })
    public ResponseEntity addReport(
            @RequestParam Long deviceId,
            @RequestParam Integer temperature,
            @RequestParam Integer gas,
            @RequestParam Integer damp,
            @RequestParam String date
            ) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(date);
            return ResponseEntity.ok().body(reportService.addReport(new ReportEntity(temperature, gas, damp, new Timestamp(parsedDate.getTime())), deviceId));
        } catch(DeviceNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping(params = { "deviceId" })
    public ResponseEntity getReport(@RequestParam Long deviceId) {
        try {
            return ResponseEntity.ok().body(reportService.getReportById(deviceId));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping
    public ResponseEntity getReport() {
        try {
            return ResponseEntity.ok().body(reportService.getReportAll());
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteReport(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(reportService.deleteReport(id));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
