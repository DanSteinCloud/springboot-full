package com.emura_group.country_geo_tracing.factory;

import org.springframework.context.annotation.Configuration;
import com.emura_group.country_geo_tracing.service.NationalRoadService;

@Configuration
public class RoadAdapterFactoryImpl implements RoadAdapterFactory {

	@Override
	public <T> RoadAdapterService<T> getService(String serviceName) {
		// TODO Auto-generated method stub
		return (RoadAdapterService<T>) new NationalRoadService();
	}  
}
