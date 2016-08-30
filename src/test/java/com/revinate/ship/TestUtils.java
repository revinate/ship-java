package com.revinate.ship;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class TestUtils {

    public static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModules(new JavaTimeModule(), new Jdk8Module())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
            .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);

    public static String classpathResourceAsString(String path) throws IOException {
        return CharStreams.toString(new InputStreamReader(TestUtils.class.getResourceAsStream(path)));
    }

    public static String classpathResourceAsUrlString(String path) throws URISyntaxException {
        return TestUtils.class.getResource(path).toString();
    }
}
