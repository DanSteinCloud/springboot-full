package com.emura_group.country_geo_tracing;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableConfigurationProperties
//@Import(AppConfig.class)
@EnableJpaRepositories(considerNestedRepositories = true, 
basePackages = {"com.emura_group.country_geo_tracing.repository"})
@ComponentScan(basePackages = {"com.emura_group.country_geo_tracing"})
public class CountryGeoTracingApplication {
	
//	@Bean
//    public RouteFactory routeFactory() {
//        return new RouteProviderFactory();
//    }
//
//    @Bean
//    public Scanner RouteScanner() {
//        return new Scanner("com.emura_group.country_geo_tracing.factory");
//    }
	
//	@Bean
//    public TypeResolver typeResolver(){
//        return new TypeResolver();
//    }

	public static void main(String[] args) {
		SpringApplication.run(CountryGeoTracingApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	    return args -> {

	        System.out.println("Let's inspect the beans provided by Spring Boot:");

	        String[] beanNames = ctx.getBeanDefinitionNames();
	        Arrays.sort(beanNames);
	        for (String beanName : beanNames) {
	            System.out.println(beanName);
	        }

	    };
	}
}
