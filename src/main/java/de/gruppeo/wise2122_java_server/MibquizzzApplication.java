package de.gruppeo.wise2122_java_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "de.gruppeo.wise2122_java_server.repository")
@EntityScan(basePackages = "de.gruppeo.wise2122_java_server.model")
@SpringBootApplication
public class MibquizzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(MibquizzzApplication.class, args);
	}

}
