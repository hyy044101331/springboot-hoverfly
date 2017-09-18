package com.mengka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 *  java -jar -Dmode=proxy demo-0.0.1-SNAPSHOT.jar
 *
 *  hoverctl start
 *
 *  hoverctl mode capture
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
