package com.emura_group.country_geo_tracing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emura_group.country_geo_tracing.model.Prefecture;
import com.emura_group.country_geo_tracing.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long>{

	List<Region> findByCountryId(Long countryId);

	void deleteByCountryId(Long countryId);


}
