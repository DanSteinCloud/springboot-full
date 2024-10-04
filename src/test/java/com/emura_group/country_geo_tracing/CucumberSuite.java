package com.emura_group.country_geo_tracing;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

//@Suite
//@IncludeEngines("cucumber")
//@SelectDirectories("src/test/java/com/emura-group/country-geo-tracing/features")
//@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.emura_group.country_geo_tracing.cucumberglue")
//@ConfigurationParameter(key = Constants.PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
//@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@negative")
public class CucumberSuite {
}