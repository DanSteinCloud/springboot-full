package com.emura_group.country_geo_tracing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emura_group.country_geo_tracing.model.Prefecture;


public interface PrefectureRepository extends JpaRepository<Prefecture, Long>{

	List<Prefecture> findByRegionId(Long regionId);

	void deleteByRegionId(Long regionId);

}
