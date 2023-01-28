package com.project.sensor.conroller;


import com.project.sensor.entity.PlaceEntity;
import com.project.sensor.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    public PlaceService placeService;

    @PostMapping
    public ResponseEntity addPlace(@RequestBody PlaceEntity placeEntity) {
        try {
            return ResponseEntity.ok().body(placeService.addPlace(placeEntity));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity getPlaceById(@RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(placeService.getPlaceById(id));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
