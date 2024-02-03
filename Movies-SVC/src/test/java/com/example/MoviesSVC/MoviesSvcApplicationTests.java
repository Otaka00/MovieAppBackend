package com.example.MoviesSVC;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = MoviesSvcApplication.class)
class MoviesSvcApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testMain() {
		MoviesSvcApplication.main(new String[] {});
		assertTrue(true);
	}
}
