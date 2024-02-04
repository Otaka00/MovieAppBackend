package com.example.MoviesSVC;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieSecurityConfigTest {

        @Autowired
        private MockMvc mockMvc;

        // Check that testEndpointWithoutUser method performs a request to the movies endpoint without any authenticated user.
        // The expectation is that the response status should be forbidden (304).
        @Test
        public void testEndpointWithoutUser() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/api/v1/movies"))
                    .andExpect(MockMvcResultMatchers.status().isForbidden());
        }


}

