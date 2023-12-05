package com.example.basfchallenge.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class CityConfig {

    CityRepository cityRepository;

    @Autowired
    public CityConfig(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(CityRepository repository){
        return args -> {
            City belgrade = new City("Belgrade", "Serbia", "Central Serbia", 2000000);
            City athens = new City("Athens", "Greece", "Attica", 3000000);
            cityRepository.saveAll(List.of(athens, belgrade));
        };



    }

}
