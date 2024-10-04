package com.emura_group.country_geo_tracing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.emura_group.country_geo_tracing.factory.RoadAdapterFactory;
import com.emura_group.country_geo_tracing.model.Road;
import com.emura_group.country_geo_tracing.repository.RoadRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class RoadController {

  @Autowired
  RoadRepository roadRepository;
  
  @Autowired
  private RoadAdapterFactory roadAdapterFactory;
  
  @PostMapping("/road")
	public void processRoadDetails(@RequestBody Road road) {
	  
	  System.out.println("Successfully created road = ' " + 
			  roadAdapterFactory.getService(road.getRoadType()).addRoadByType(road).getRoadName()+ " ' to the RoadTopic");
	  
	  roadRepository.save(new Road(
			  roadAdapterFactory.getService(road.getRoadType()).addRoadByType(road).getRoadType(),
			  roadAdapterFactory.getService(road.getRoadType()).addRoadByType(road).getRoadName(),
			  roadAdapterFactory.getService(road.getRoadType()).addRoadByType(road).getWidth(),
			  roadAdapterFactory.getService(road.getRoadType()).addRoadByType(road).getCover(),
			  roadAdapterFactory.getService(road.getRoadType()).addRoadByType(road).getState(),
			  roadAdapterFactory.getService(road.getRoadType()).addRoadByType(road).getDescription()
			  ));		
  }
  
  @GetMapping("roads/{roadType}")
 	public List<Road> retreivingRoadsByType(@PathVariable("roadType") String roadType) {
 		//return roadAdapterFactory.getService(roadType).findAllRoads(roadType);
	  return (List<Road>) roadRepository.findByRoadType(roadType);
  }

  @GetMapping("/roads")
  public ResponseEntity<List<Road>> getAllRoads(@RequestParam(required = false) String Road_name) {
    List<Road> Roads = new ArrayList<Road>();

    if (Road_name == null)
    	roadRepository.findAll().forEach(Roads::add);
    else
      roadRepository.findByRoadType(Road_name).forEach(Roads::add);

    if (Roads.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(Roads, HttpStatus.OK);
  }

  @GetMapping("/road/{id}")
  public ResponseEntity<Road> getRoadById(@PathVariable("id") long id) {
    Road Road = roadRepository.findById(id)
    		.orElseThrow(() -> new ResourceNotFoundException("Not found Road with id = " + id));

    return new ResponseEntity<>(Road, HttpStatus.OK);
  }

//  @PostMapping("/road")
//  public ResponseEntity<Road> createRoad(@RequestBody Road road) {
//    Road _road = roadRepository
//    		.save(new Road(
//    		          road.getRoadType(),
//                      road.getRoadName(),
//                      road.getWidth(),
//                      road.getCover(),
//    		          road.getState(),
//                      road.getDescription()
//    		));
//    return new ResponseEntity<>(_road, HttpStatus.CREATED);
//  }

  @PutMapping("/road/{id}")
  public ResponseEntity<Road> updateRoad(@PathVariable("id") long id, @RequestBody Road Road) {
    Road _Road = roadRepository
    	.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Road with id = " + id));

    _Road.setRoadType(Road.getRoadType());
    _Road.setRoadName(Road.getRoadName());
    _Road.setDescription(Road.getDescription());
    
    return new ResponseEntity<>(roadRepository.save(_Road), HttpStatus.OK);
  }

  @DeleteMapping("/road/delete/{id}")
  public ResponseEntity<HttpStatus> deleteRoad(@PathVariable("id") long id) {
    roadRepository.deleteById(id);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/roads/delete")
  public ResponseEntity<HttpStatus> deleteAllRoads() {
    roadRepository.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } 
  }
  