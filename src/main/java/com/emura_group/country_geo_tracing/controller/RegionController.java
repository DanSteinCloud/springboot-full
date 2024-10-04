package com.emura_group.country_geo_tracing.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.emura_group.country_geo_tracing.exception.ResourceNotFoundException;
import com.emura_group.country_geo_tracing.model.Region;
import com.emura_group.country_geo_tracing.repository.CountryRepository;
import com.emura_group.country_geo_tracing.repository.RegionRepository;
import com.emura_group.country_geo_tracing.exception.ResourceNotFoundException;



@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RegionController {

  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private RegionRepository regionRepository;

  @GetMapping("/country/{countryId}/regions")
  public ResponseEntity<List<Region>> getAllregionsByCountryId(@PathVariable(value = "countryId") Long CountryId) {
    if (!countryRepository.existsById(CountryId)) {
      throw new ResourceNotFoundException("Not found Country with id = " + CountryId);
    }

    List<Region> region = regionRepository.findByCountryId(CountryId);
    return new ResponseEntity<>(region, HttpStatus.OK);
  }

  @GetMapping("/region/{id}")
  public ResponseEntity<Region> getregionsByCountryId(@PathVariable(value = "id") Long id) {
    Region region = regionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found region with id = " + id));

    return new ResponseEntity<>(region, HttpStatus.OK);
  }

  @PostMapping("/country/{countryId}/region")
  public ResponseEntity<Region> createregion(@PathVariable(value = "countryId") Long CountryId,
      @RequestBody Region regionRequest) {
    Region region = countryRepository.findById(CountryId).map(Country -> {
      regionRequest.setCountry(Country);
      return regionRepository.save(regionRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found Country with id = " + CountryId));

    return new ResponseEntity<>(region, HttpStatus.CREATED);
  }

  @PutMapping("/region/{id}")
  public ResponseEntity<Region> updateregion(@PathVariable("id") long id, @RequestBody Region regionRequest) {
    Region region = regionRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("regionId " + id + "not found"));

    region.setDescription(regionRequest.getDescription());

    return new ResponseEntity<>(regionRepository.save(region), HttpStatus.OK);
  }

  @DeleteMapping("/region/{id}")
  public ResponseEntity<HttpStatus> deleteregion(@PathVariable("id") long id) {
    regionRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/country/{countryId}/regions")
  public ResponseEntity<List<Region>> deleteAllregionsOfCountry(@PathVariable(value = "CountryId") Long CountryId) {
    if (!countryRepository.existsById(CountryId)) {
      throw new ResourceNotFoundException("Not found Country with id = " + CountryId);
    }

    regionRepository.deleteByCountryId(CountryId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
