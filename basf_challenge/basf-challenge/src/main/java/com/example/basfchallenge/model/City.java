package com.example.basfchallenge.model;

import jakarta.persistence.*;
//import org.springframework.data.annotation.Id;
@Entity
@Table
public class City {
    @Id
    @SequenceGenerator(
            name = "city_sequence",
            sequenceName = "city_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_sequence"
    )

    private int id;
    private String name;
    private String country;
    private String state_or_region;
    private int population;

    private double temp_c;

    public City() {}

    public City(int id, String name, String country, String state_or_region, int population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.state_or_region = state_or_region;
        this.population = population;

    }

    public City(String name, String country, String state_or_region, int population) {
        this.name = name;
        this.country = country;
        this.state_or_region = state_or_region;
        this.population = population;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState_or_region() {
        return state_or_region;
    }

    public void setState_or_region(String state_or_region) {
        this.state_or_region = state_or_region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
