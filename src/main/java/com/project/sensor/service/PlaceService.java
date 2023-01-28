package com.project.sensor.service;


import com.project.sensor.entity.PlaceEntity;
import com.project.sensor.model.Place;
import com.project.sensor.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    @Autowired
    public PlaceRepository placeRepository;

    public Place addPlace(PlaceEntity place) {
        return Place.toModel(placeRepository.save(place));
    }

    public Place getPlaceById(Long id) {
        PlaceEntity placeEntity = placeRepository.findById(id).get();
        return Place.toModel(placeRepository.save(placeEntity));
    }
}
