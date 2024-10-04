package com.emura_group.country_geo_tracing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emura_group.country_geo_tracing.model.Quartier;


public interface QuartierRepository extends JpaRepository<Quartier, Long>{

	List<Quartier> findByCommuneId(Long prefectureId);

//	List<Quartier> findQuartiersByRouteId(Long routeId);

}
