package com.example.basfchallenge.service;
import com.example.basfchallenge.model.City;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.basfchallenge.model.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> cityList;

    public List<City> getCities(){
        return cityRepository.findAll();
    }

    public double getCityWeather(String cityName) {
        String url = "http://api.weatherapi.com/v1/current.json?key=81bc9777eff040cb984234705230212&q="+cityName+"&aqi=no";
        WebClient.Builder builder = WebClient.builder();
        String json_resp = builder.build().get().uri(url).retrieve().bodyToMono(String.class).block();
//        System.out.println(json_resp);
        Pattern pattern = Pattern.compile("\"name\"\s*:\s*\"(.*?)\",.*?\"temp_c\"\s*:\s*(.*?),", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(json_resp);
        boolean matchFound = matcher.find();
        double temp_c = 0.0;
        if(matchFound) {
            String name = matcher.group(1);
            if (!cityName.equals(name))
                throw new IllegalStateException("There isn't a city with this name!");

//            System.out.println("----------------------------------");
//            System.out.println(matcher.group(2));
            temp_c = Double.parseDouble(matcher.group(2));
//            System.out.println("----------------------------------");
        } else {
            throw new IllegalStateException("There isn't a city with this name!");
        }

        return temp_c;
    }


    public void addCity(City city) {

        double temp_c = getCityWeather(city.getName());
        if (cityRepository.findCityByName(city.getName()).isPresent())
            throw new IllegalStateException("City you are trying to add already exists in DB.");
        city.setTemp_c(temp_c);
        cityRepository.save(city);


    }

    public void removeCity(int id) {
        boolean exist = cityRepository.existsById(id);
        if (!exist)
            throw new IllegalStateException("No city with id=" + id + " found in DB.");
        cityRepository.deleteById(id);
    }

    public void updateCity(City new_city_data){
        if (new_city_data.getId() == 0)
            throw new IllegalStateException("No city id provided in request body.");

        System.out.println(new_city_data.toString());
        Optional<City> res = cityRepository.findById(new_city_data.getId());
        City city = res.orElse(null);
        if (city != null){
            String country = new_city_data.getCountry();
            String state_or_region = new_city_data.getState_or_region();
            int population = new_city_data.getPopulation();
            
            if ((state_or_region != null) && !state_or_region.equals(city.getState_or_region())){
                city.setState_or_region(state_or_region);
            }
            if ((country != null) && !country.equals(city.getCountry())){
                city.setCountry(country);
            }
            if ((population != 0) && (population != city.getPopulation())){
                city.setPopulation(population);
            }
            cityRepository.save(city);
        }
        else throw new IllegalStateException("No city with id=" + new_city_data.getId() + " found in DB.");
    }

    public City getCity(int id) {
        City city = cityRepository.findById(id).orElse(null);
        if (city == null){
            throw new IllegalStateException("No city with id=" + id + " found in DB.");
        }
        return city;
    }
}
