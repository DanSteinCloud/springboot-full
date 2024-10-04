package com.emura_group.country_geo_tracing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emura_group.country_geo_tracing.exception.ResourceNotFoundException;
import com.emura_group.country_geo_tracing.model.Country;
import com.emura_group.country_geo_tracing.repository.CountryRepository;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CountryController {

  @Autowired
  CountryRepository countryRepository;

  @GetMapping("/countries")
  public ResponseEntity<List<Country>> getAllCountrys(@RequestParam(required = false) String Country_name) {
    List<Country> Countrys = new ArrayList<Country>();

    if (Country_name == null)
    	countryRepository.findAll().forEach(Countrys::add);
    else
      countryRepository.findByCountryName(Country_name).forEach(Countrys::add);

    if (Countrys.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(Countrys, HttpStatus.OK);
  }

  @GetMapping("/country/{id}")
  public ResponseEntity<Country> getCountryById(@PathVariable("id") long id) {
    Country Country = countryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Country with id = " + id));

    return new ResponseEntity<>(Country, HttpStatus.OK);
  }

  @PostMapping("/country")
  public ResponseEntity<Country> createCountry(@RequestBody Country Country) {
    Country _Country = countryRepository.save(new Country(5,
    		                                          Country.getCountryCode(),
                                                      Country.getCountryAcronyme(),
                                                      Country.getCountryName(),
                                                      Country.getCountry_flag(),
    		                                          Country.getCountry_population(),
    		                                          Country.getCountry_men_population(),
    		                                          Country.getCountry_women_population(),
    		                                          Country.getCountry_children_population(),
    		                                          Country.getCountryMainCrop(),
    		                                          Country.getRoads()
    		                                          ));
    return new ResponseEntity<>(_Country, HttpStatus.CREATED);
  }

  @PutMapping("/country/{id}")
  public ResponseEntity<Country> updateCountry(@PathVariable("id") long id, @RequestBody Country Country) {
    Country _Country = countryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Country with id = " + id));

    _Country.setCountryCode(Country.getCountryCode());
    _Country.setCountryAcronyme(Country.getCountryAcronyme());
    _Country.setCountryName(Country.getCountryName());
    _Country.setCountry_flag(Country.getCountry_flag());
    _Country.setCountry_women_population(Country.getCountry_men_population());
    _Country.setCountry_women_population(Country.getCountry_women_population());
    _Country.setCountry_children_population(Country.getCountry_children_population());
    _Country.setCountryMainCrop(Country.getCountryMainCrop());
    
    return new ResponseEntity<>(countryRepository.save(_Country), HttpStatus.OK);
  }

  @DeleteMapping("/country/{id}")
  public ResponseEntity<HttpStatus> deleteCountry(@PathVariable("id") long id) {
    countryRepository.deleteById(id);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/countries")
  public ResponseEntity<HttpStatus> deleteAllCountrys() {
    countryRepository.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } 

  @GetMapping("/countries-of/{crop}")
  public ResponseEntity<List<Country>> findCountryByCrops(@PathVariable("crop") String crop) {
    List<Country> Countrys = countryRepository.findByCountryMainCrop(crop);

    if (Countrys.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    return new ResponseEntity<>(Countrys, HttpStatus.OK);
  }
}