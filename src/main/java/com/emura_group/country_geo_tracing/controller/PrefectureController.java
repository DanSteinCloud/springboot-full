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
import com.emura_group.country_geo_tracing.model.Prefecture;
import com.emura_group.country_geo_tracing.repository.PrefectureRepository;
import com.emura_group.country_geo_tracing.repository.RegionRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PrefectureController {

  @Autowired
  private RegionRepository regionRepository;

  @Autowired
  private PrefectureRepository prefectureRepository;

  @GetMapping("/region/{regionId}/prefectures")
  public ResponseEntity<List<Prefecture>> getAllprefecturesByregionId(@PathVariable(value = "regionId") Long regionId) {
    if (!regionRepository.existsById(regionId)) {
      throw new ResourceNotFoundException("Not found region with id = " + regionId);
    }

    List<Prefecture> prefecture = prefectureRepository.findByRegionId(regionId);
    return new ResponseEntity<>(prefecture, HttpStatus.OK);
  }

  @GetMapping("/prefecture/{id}")
  public ResponseEntity<Prefecture> getprefecturesByregionId(@PathVariable(value = "id") Long id) {
    Prefecture prefecture = prefectureRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found prefecture with id = " + id));

    return new ResponseEntity<>(prefecture, HttpStatus.OK);
  }

  @PostMapping("/region/{regionId}/prefecture")
  public ResponseEntity<Prefecture> createprefecture(@PathVariable(value = "regionId") Long regionId,
      @RequestBody Prefecture prefectureRequest) {
    Prefecture prefecture = regionRepository.findById(regionId).map(region -> {
      prefectureRequest.setRegion(region);
      return prefectureRepository.save(prefectureRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found region with id = " + regionId));

    return new ResponseEntity<>(prefecture, HttpStatus.CREATED);
  }

  @PutMapping("/prefecture/{id}")
  public ResponseEntity<Prefecture> updateprefecture(@PathVariable("id") long id, @RequestBody Prefecture prefectureRequest) {
    Prefecture prefecture = prefectureRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("prefectureId " + id + "not found"));

    prefecture.setDescription(prefectureRequest.getDescription());

    return new ResponseEntity<>(prefectureRepository.save(prefecture), HttpStatus.OK);
  }

  @DeleteMapping("/prefecture/{id}")
  public ResponseEntity<HttpStatus> deleteprefecture(@PathVariable("id") long id) {
    prefectureRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/region/{regionId}/prefectures")
  public ResponseEntity<List<Prefecture>> deleteAllprefecturesOfregion(@PathVariable(value = "regionId") Long regionId) {
    if (!regionRepository.existsById(regionId)) {
      throw new ResourceNotFoundException("Not found region with id = " + regionId);
    }

    prefectureRepository.deleteByRegionId(regionId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}