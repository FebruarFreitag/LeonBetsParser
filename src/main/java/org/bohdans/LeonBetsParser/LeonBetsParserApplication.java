package org.bohdans.LeonBetsParser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Since there were no specified requirements about general look of the test task app, I've decided to implement it as
 * Spring-based web app with a single endpoint that triggers data fetching;
 */
@SpringBootApplication
public class LeonBetsParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeonBetsParserApplication.class, args);
	}

}
