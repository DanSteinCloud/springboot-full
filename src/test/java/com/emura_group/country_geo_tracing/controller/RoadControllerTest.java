package com.emura_group.country_geo_tracing.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.emura_group.country_geo_tracing.model.Road;
import com.emura_group.country_geo_tracing.repository.RoadRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoadController.class)
@AutoConfigureMockMvc
public class RoadControllerTest {
	
	@Autowired
    MockMvc mockMvc;

    @MockBean
    RoadRepository roadRepository;
    
    

    List<Road> roads = new ArrayList<>();
    
    @BeforeEach
    void setUp() {
        roads = List.of(
                new Road("national", "№ 1", 30, "tar", "fine", "national road"),
                new Road("national", "№ 2", 30, "tar", "fine", "national road number 2")
        );
    }

    @Test
    void shouldFindAllRoads() throws Exception {
        String jsonResponse = """
                [
                    {
                        "id":1,
                        "userId":1,
                        "title":"Hello, World!",
                        "body":"This is my first post.",
                        "version": null
                    },
                    {
                        "id":2,
                        "userId":1,
                        "title":"Second Post",
                        "body":"This is my second post.",
                        "version": null
                    }
                ]
                """;

        when(roadRepository.findAll()).thenReturn(roads);

        ResultActions resultActions = mockMvc.perform(get("/api/roads"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));

        JSONAssert.assertEquals(jsonResponse, resultActions.andReturn().getResponse().getContentAsString(), false);

    }

    
    @Test
    void shouldFindRoadWhenGivenValidId() throws Exception {
        Road road = new Road(52, "national", "№ 1", 30, "tar", "fine", "national road");
        when(roadRepository.findById((long) 1)).thenReturn(Optional.of(road));
        @SuppressWarnings("preview")
		String json = STR."""
                {
                    "id": 52,
					"roadType": "national",
					"roadName": "№ 1",
					"width": 30,
					"cover": "tar",
					"state": "fine",
					"description": "national road",
                }
                """;

        mockMvc.perform(get("/api/road/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }
    
    @Test
    void shouldCreateNewRoadWhenGivenRoadType() throws Exception {
        Road road = new Road(52, "national", "№ 1", 30, "tar", "fine", "national road");
        when(roadRepository.save(road)).thenReturn(road);
        @SuppressWarnings("preview")
		String json = STR."""
                {
                    "id":\{road.getId()},
                    "userId":\{road.getRoadType()},
                    "title":"\{road.getRoadName()}",
                    "title":"\{road.getWidth()}",
                    "title":"\{road.getCover()}",
                    "title":"\{road.getState()}",
                    "title":"\{road.getDescription()}"
                }
                """;

        mockMvc.perform(post("/api/road")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().json(json));
    }
    
    
    @Test
    void shouldUpdateRoadWhenGivenValidRoad() throws Exception {
        Road updated = new Road(52, "national", "№ 1", 30, "tar", "fine", "national road");
        when(roadRepository.findById((long) 1)).thenReturn(Optional.of(roads.get(0)));
        when(roadRepository.save(updated)).thenReturn(updated);
        @SuppressWarnings("preview")
		String requestBody = STR."""
                {
                    "id":\{updated.getId()},
                    "userId":\{updated.getRoadType()},
                    "title":"\{updated.getRoadName()}",
                    "title":"\{updated.getWidth()}",
                    "title":"\{updated.getCover()}",
                    "title":"\{updated.getState()}",
                    "title":"\{updated.getDescription()}"
                }
                """;

        mockMvc.perform(put("/api/road/1")
                .contentType("application/json")
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void shouldNotUpdateAndThrowNotFoundWhenGivenAnInvalidRoadID() throws Exception {
        Road updated = new Road(52, "national", "№ 1", 30, "tar", "fine", "national road");
        when(roadRepository.save(updated)).thenReturn(updated);
        @SuppressWarnings("preview")
		String json = STR."""
                {
                    "id":\{updated.getId()},
                    "userId":\{updated.getRoadType()},
                    "title":"\{updated.getRoadName()}",
                    "title":"\{updated.getWidth()}",
                    "title":"\{updated.getCover()}",
                    "title":"\{updated.getState()}",
                    "title":"\{updated.getDescription()}"
                }
                """;

        mockMvc.perform(put("/api/road/999")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteRoadWhenGivenValidID() throws Exception {
        doNothing().when(roadRepository).deleteById((long) 1);

        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isNoContent());

        verify(roadRepository, times(1)).deleteById((long) 1);
    }


}
