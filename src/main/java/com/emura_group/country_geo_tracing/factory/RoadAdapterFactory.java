package com.emura_group.country_geo_tracing.factory;

import java.util.List;

import com.emura_group.country_geo_tracing.model.Road;

public interface RoadAdapterFactory {
public <T> RoadAdapterService<T> getService(String serviceName);
}
