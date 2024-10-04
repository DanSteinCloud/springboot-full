package com.emura_group.country_geo_tracing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.emura_group.country_geo_tracing.factory.RoadAdapterService;
import com.emura_group.country_geo_tracing.model.Road;
import com.emura_group.country_geo_tracing.repository.RoadRepository;

@Service("regional")
public class RegionalRoadService implements RoadAdapterService<Road>{
	@Autowired
	private RoadRepository roadRepository;
	
	
	public RegionalRoadService() {
	}
	
	public RegionalRoadService(RoadRepository roadRepository) {
	System.out.println("Road Repo:= " + this.roadRepository);
	this.roadRepository = roadRepository;
	}

    
	@Override
	public Road addRoadByType(Road road) {
	Road _road = new Road();
	     _road.setRoadType("regional");
	     _road.setRoadName(road.getRoadName());
	     _road.setWidth(road.getWidth());
	     _road.setCover(road.getCover());
	     _road.setState(road.getState());
	     _road.setDescription(road.getDescription());
		
	System.out.println("This is a regional road creation - "  + _road.toString());
	return _road;
	}
	
}