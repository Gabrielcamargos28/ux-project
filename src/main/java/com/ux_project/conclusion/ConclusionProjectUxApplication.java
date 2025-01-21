package com.ux_project.conclusion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.ux_project.conclusion")
@EntityScan(basePackages = "com.ux_project.conclusion.domain")
@SpringBootApplication
public class ConclusionProjectUxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConclusionProjectUxApplication.class, args);
	}

}
