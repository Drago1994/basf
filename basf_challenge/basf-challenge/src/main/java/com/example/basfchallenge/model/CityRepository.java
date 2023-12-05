package com.example.basfchallenge.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Integer> {
    @Query("SELECT c FROM City c Where c.name = ?1")
    Optional<City> findCityByName(String name);

}
