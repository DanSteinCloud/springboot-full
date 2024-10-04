package com.emura_group.country_geo_tracing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emura_group.country_geo_tracing.model.Road;

@Repository
public interface RoadRepository  extends JpaRepository<Road, Long>{
	
	@Query(value = "SELECT * FROM location.road WHERE road_type=?",nativeQuery = true)
	Iterable<Road> findByRoadType(String road_type);

}
