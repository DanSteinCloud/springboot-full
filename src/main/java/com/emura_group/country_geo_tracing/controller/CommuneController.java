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
import com.emura_group.country_geo_tracing.model.Commune;
import com.emura_group.country_geo_tracing.repository.CommuneRepository;
import com.emura_group.country_geo_tracing.repository.PrefectureRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CommuneController {

  @Autowired
  private PrefectureRepository prefectureRepository;

  @Autowired
  private CommuneRepository communeRepository;

  @GetMapping("/prefecture/{prefectureId}/communes")
  public ResponseEntity<List<Commune>> getAllcommunesByprefectureId(@PathVariable(value = "prefectureId") Long prefectureId) {
    if (!prefectureRepository.existsById(prefectureId)) {
      throw new ResourceNotFoundException("Not found prefecture with id = " + prefectureId);
    }

    List<Commune> commune = communeRepository.findByPrefectureId(prefectureId);
    return new ResponseEntity<>(commune, HttpStatus.OK);
  }

  @GetMapping("/commune/{id}")
  public ResponseEntity<Commune> getcommunesByprefectureId(@PathVariable(value = "id") Long id) {
    Commune commune = communeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found commune with id = " + id));

    return new ResponseEntity<>(commune, HttpStatus.OK);
  }

  @PostMapping("/prefecture/{prefectureId}/commune")
  public ResponseEntity<Commune> createcommune(@PathVariable(value = "prefectureId") Long prefectureId,
      @RequestBody Commune communeRequest) {
    Commune commune = prefectureRepository.findById(prefectureId).map(prefecture -> {
      communeRequest.setPrefecture(prefecture);
      return communeRepository.save(communeRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found prefecture with id = " + prefectureId));

    return new ResponseEntity<>(commune, HttpStatus.CREATED);
  }

  @PutMapping("/commune/{id}")
  public ResponseEntity<Commune> updatecommune(@PathVariable("id") long id, @RequestBody Commune communeRequest) {
    Commune commune = communeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("communeId " + id + "not found"));

    commune.setDescription(communeRequest.getDescription());

    return new ResponseEntity<>(communeRepository.save(commune), HttpStatus.OK);
  }

  @DeleteMapping("/commune/{id}")
  public ResponseEntity<HttpStatus> deletecommune(@PathVariable("id") long id) {
    communeRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/prefecture/{prefectureId}/communes")
  public ResponseEntity<List<Commune>> deleteAllcommunesOfprefecture(@PathVariable(value = "prefectureId") Long prefectureId) {
    if (!prefectureRepository.existsById(prefectureId)) {
      throw new ResourceNotFoundException("Not found prefecture with id = " + prefectureId);
    }

    communeRepository.deleteByPrefectureId(prefectureId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  }
