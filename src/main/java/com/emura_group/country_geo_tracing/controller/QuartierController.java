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
import com.emura_group.country_geo_tracing.model.Quartier;
import com.emura_group.country_geo_tracing.repository.CommuneRepository;
import com.emura_group.country_geo_tracing.repository.QuartierRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class QuartierController {

  @Autowired
  private CommuneRepository communeRepository;

  @Autowired
  private QuartierRepository quartierRepository;

  @GetMapping("/commune/{communeId}/quartiers")
  public ResponseEntity<List<Quartier>> getAllquartiersBycommuneId(@PathVariable(value = "communeId") Long communeId) {
    if (!communeRepository.existsById(communeId)) {
      throw new ResourceNotFoundException("Not found commune with id = " + communeId);
    }

    List<Quartier> quartier = quartierRepository.findByCommuneId(communeId);
    return new ResponseEntity<>(quartier, HttpStatus.OK);
  }

  @GetMapping("/quartier/{id}")
  public ResponseEntity<Quartier> getquartiersBycommuneId(@PathVariable(value = "id") Long id) {
    Quartier quartier = quartierRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found quartier with id = " + id));

    return new ResponseEntity<>(quartier, HttpStatus.OK);
  }

  @PostMapping("/commune/{communeId}/quartier")
  public ResponseEntity<Quartier> createquartier(@PathVariable(value = "communeId") Long communeId,
      @RequestBody Quartier quartierRequest) {
    Quartier quartier = communeRepository.findById(communeId).map(commune -> {
      quartierRequest.setCommune(commune);
      return quartierRepository.save(quartierRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found commune with id = " + communeId));

    return new ResponseEntity<>(quartier, HttpStatus.CREATED);
  }

  @PutMapping("/quartier/{id}")
  public ResponseEntity<Quartier> updatequartier(@PathVariable("id") long id, @RequestBody Quartier quartierRequest) {
    Quartier quartier = quartierRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("quartierId " + id + "not found"));

    quartier.setDescription(quartierRequest.getDescription());

    return new ResponseEntity<>(quartierRepository.save(quartier), HttpStatus.OK);
  }

  @DeleteMapping("/quartier/{id}")
  public ResponseEntity<HttpStatus> deletequartier(@PathVariable("id") long id) {
    quartierRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/commune/{communeId}/quartiers")
  public ResponseEntity<List<Quartier>> deleteAllquartiersOfcommune(@PathVariable(value = "communeId") Long communeId) {
    if (!communeRepository.existsById(communeId)) {
      throw new ResourceNotFoundException("Not found commune with id = " + communeId);
    }

    quartierRepository.findByCommuneId(communeId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  }
