package com.emura_group.country_geo_tracing.factory;

import java.util.List;

import com.emura_group.country_geo_tracing.model.Road;

public interface RoadAdapterService<T> {
	Road addRoadByType(Road road);
}