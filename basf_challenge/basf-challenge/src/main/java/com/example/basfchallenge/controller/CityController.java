package com.example.basfchallenge.controller;
import com.example.basfchallenge.model.City;
import com.example.basfchallenge.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.basfchallenge.model.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping(path="/city")
public class CityController {

    private CityService cityService;
    @Autowired
    public CityController(CityService cityService){
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public City getCity(@PathVariable("id") int id){
        return cityService.getCity(id);
    }
    @GetMapping("/")
    public List<City> getCity(){
        return cityService.getCities();
    }

    @PostMapping
    public void createCity(@RequestBody City city){
        cityService.addCity(city);
    }
    @DeleteMapping(path="{id}")
    public void deleteCity(@PathVariable("id") int id){
        cityService.removeCity(id);
    }

    @PutMapping
    public void updateCity(@RequestBody City city){
        cityService.updateCity(city);
    }

}
