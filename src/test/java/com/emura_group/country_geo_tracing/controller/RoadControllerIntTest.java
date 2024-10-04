package com.emura_group.country_geo_tracing.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.emura_group.country_geo_tracing.model.Road;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class RoadControllerIntTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void connectionEstablished() {
        assertThat(postgres.isCreated()).isTrue();
        assertThat(postgres.isRunning()).isTrue();
    }

    @Test
    void shouldFindAllPosts() {
        Road[] roads = restTemplate.getForObject("/api/roads", Road[].class);
        assertThat(roads.length).isGreaterThan(10);
    }
    
    @Test
    void shouldFindRoadWhenValidRoadID() {
        ResponseEntity<Road> response = restTemplate.exchange("/api/road/1", HttpMethod.GET, null, Road.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void shouldThrowNotFoundWhenInvalidRoadID() {
        ResponseEntity<Road> response = restTemplate.exchange("/api/road/999", HttpMethod.GET, null, Road.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Rollback
    void shouldCreateNewRoadWhenReauestBodyIsValid() {
        Road post = new Road("national", "№ 1", 30, "tar", "fine", "national road");

        ResponseEntity<Road> response = restTemplate.exchange("/api/road", HttpMethod.POST, new HttpEntity<Road>(post), Road.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getId()).isEqualTo(101);
        assertThat(response.getBody().getRoadType()).isEqualTo("national");
        assertThat(response.getBody().getWidth()).isEqualTo(30);
        assertThat(response.getBody().getCover()).isEqualTo("tar");
        assertThat(response.getBody().getState()).isEqualTo("fine");
        assertThat(response.getBody().getDescription()).isEqualTo("national road");
    }

    @Test
    void shouldNotCreateNewPostWhenValidationFails() {
        Road post = new Road("national", "№ 1", 30, "tar", "fine", null);
        ResponseEntity<Road> response = restTemplate.exchange("/api/road", HttpMethod.POST, 
        		new HttpEntity<Road>(post), Road.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @Rollback
    void shouldUpdatePostWhenPostIsValid() {
        ResponseEntity<Road> response = restTemplate.exchange("/api/road/99", HttpMethod.GET, null, Road.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Road existing = response.getBody();
        assertThat(existing).isNotNull();
        Road updated = new Road(existing.getId(),
        		                existing.getRoadType(),
        		                existing.getRoadName(),
        		                35,
        		                existing.getCover(),
        		                "broken",
        		                "Route principale");

        assertThat(updated.getWidth()).isEqualTo(35);
        assertThat(updated.getState()).isEqualTo("broken");
        assertThat(updated.getDescription()).isEqualTo("Route principale");
    }

    @Test
    @Rollback
    void shouldDeleteWithValidID() {
        ResponseEntity<Void> response = restTemplate.exchange("/api/road/88", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}