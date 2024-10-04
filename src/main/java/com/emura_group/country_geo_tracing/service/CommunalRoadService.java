package com.emura_group.country_geo_tracing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.emura_group.country_geo_tracing.factory.RoadAdapterService;
import com.emura_group.country_geo_tracing.model.Road;
import com.emura_group.country_geo_tracing.repository.RoadRepository;

import jakarta.persistence.EntityManager;

@Service("communal")
//@Qualifier("nationale")
public class CommunalRoadService implements RoadAdapterService<Road>{
	@Autowired
	private RoadRepository roadRepository;
	
	
	public CommunalRoadService() {
	}
	
	public CommunalRoadService(RoadRepository roadRepository) {
	System.out.println("Road Repo:= " + this.roadRepository);
	this.roadRepository = roadRepository;
	}

    
	@Override
	public Road addRoadByType(Road road) {
	Road _road = new Road();
	     _road.setRoadType("communal");
	     _road.setRoadName(road.getRoadName());
	     _road.setWidth(road.getWidth());
	     _road.setCover(road.getCover());
	     _road.setState(road.getState());
	     _road.setDescription(road.getDescription());
		
	System.out.println("This is a fastway road creation - "  + _road.toString());
	return _road;
	}
	
}