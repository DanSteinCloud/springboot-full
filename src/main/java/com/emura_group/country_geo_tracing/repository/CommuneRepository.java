package com.emura_group.country_geo_tracing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emura_group.country_geo_tracing.model.Commune;


public interface CommuneRepository extends JpaRepository<Commune, Long>{

	List<Commune> findByPrefectureId(Long prefectureId);

	void deleteByPrefectureId(Long prefectureId);

}
