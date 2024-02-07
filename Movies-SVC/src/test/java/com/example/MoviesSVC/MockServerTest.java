package com.example.MoviesSVC;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MockServerTest {

    @Test
    public void testMockServer() throws Exception {
        stubFor(get(urlEqualTo("/api/v1/movies"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Hello, World!\"}")));

        URL url = new URL("http://localhost:8080/api/v1/movies");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        assertThat(connection.getResponseCode(), is(200));
        assertThat(connection.getHeaderField("Content-Type"), is("application/json"));
        assertThat(new BufferedReader(new InputStreamReader(connection.getInputStream()))
                .lines().collect(Collectors.joining()), is("{\"message\": \"Hello, World!\"}"));
    }

}