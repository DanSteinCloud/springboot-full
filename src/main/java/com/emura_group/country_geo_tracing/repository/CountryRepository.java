package com.emura_group.country_geo_tracing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emura_group.country_geo_tracing.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

	List<Country> findByCountryMainCrop(String crop);

	List<Country> findByCountryName(String country_name);

}
