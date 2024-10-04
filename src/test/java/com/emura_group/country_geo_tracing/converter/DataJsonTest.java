package com.emura_group.country_geo_tracing.converter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.emura_group.country_geo_tracing.model.Road;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class DataJsonTest {

    @Autowired
    private JacksonTester<Road> jacksonTester;

    @Test
    void shouldSerializePost() throws Exception {
        Road road = new Road(52, "national", "№ 1", 30, "tar", "fine", "national road");
        @SuppressWarnings("preview")
		String expected = STR."""
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
        assertThat(jacksonTester.write(road)).isEqualToJson(expected);
    }

    @Test
    void shouldDeserializePost() throws Exception {
        Road road = new Road(1, "national", "№ 1", 30, "tar", "fine", "national road");
        @SuppressWarnings("preview")
		String content = STR."""
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

        assertThat(jacksonTester.parse(content)).isEqualTo(road);
        assertThat(jacksonTester.parseObject(content).getId()).isEqualTo(1);
        assertThat(jacksonTester.parseObject(content).getRoadType()).isEqualTo("national");
        assertThat(jacksonTester.parseObject(content).getRoadName()).isEqualTo("№ 1");
        assertThat(jacksonTester.parseObject(content).getWidth()).isEqualTo(30);
        assertThat(jacksonTester.parseObject(content).getCover()).isEqualTo("tar");
        assertThat(jacksonTester.parseObject(content).getState()).isEqualTo("fine");
        assertThat(jacksonTester.parseObject(content).getDescription()).isEqualTo("national road");
    }

}