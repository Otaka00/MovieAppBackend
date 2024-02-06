package com.Sumerge.MovieApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MovieAppApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void testMain() {
		MovieAppApplication.main(new String[] {});
		assertTrue(true);
	}
}
