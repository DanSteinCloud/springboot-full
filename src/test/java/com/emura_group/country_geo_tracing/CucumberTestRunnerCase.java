package com.emura_group.country_geo_tracing;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty"},
        glue = {"com.emura_group.country_geo_tracing.cucumberglue"})
public class CucumberTestRunnerCase {
}