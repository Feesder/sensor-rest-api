package com.project.sensor.model;

import com.project.sensor.entity.PlaceEntity;

import javax.persistence.Column;

public class Place {
    private Long id;
    private String region;
    private String city;
    private String street;
    private String house;

    public Place(Long id, String region, String city, String street, String house) {
        this.id = id;
        this.region = region;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public static Place toModel(PlaceEntity place) {
        return new Place(place.getId(), place.getRegion(), place.getCity(), place.getStreet(), place.getHouse());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
