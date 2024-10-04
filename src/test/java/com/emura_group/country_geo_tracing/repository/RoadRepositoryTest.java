package com.emura_group.country_geo_tracing.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;


import com.emura_group.country_geo_tracing.model.Road;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoadRepositoryTest {

//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");
//
//    @Autowired
//    RoadRepository roadRepository;
//
//    @Autowired
//    JdbcConnectionDetails jdbcConnectionDetails;
//
//    @BeforeEach
//    void setUp() {
//        List<Road> roads = List.of(new Road(52, "national", "№ 1", 30, "tar", "fine", "national road"));
//        roadRepository.saveAll(roads);
//    }
//
//    @Test
//    void connectionEstablished() {
//        assertThat(postgres.isCreated()).isTrue();
//        assertThat(postgres.isRunning()).isTrue();
//    }
//
//    @Test
//    void shouldReturnRoadByType() {
//        //Road road = roadRepository.findByRoadType("national").orElseThrow();
//        Iterable<Road> road = roadRepository.findByRoadType("national");
//        assertEquals("national", ((Road) road).getRoadType(), "Road type should be 'national'");
//    }
//
//    @Test
//    void shouldNotReturnRoadWhenTypeIsNotFound() {
//        Optional<Iterable<Road>> road = Optional.ofNullable(roadRepository.findByRoadType("national"));
//        assertFalse(road.isPresent(), "Road should not be present");
//    }

}