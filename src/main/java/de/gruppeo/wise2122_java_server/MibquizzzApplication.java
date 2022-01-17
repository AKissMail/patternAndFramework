package de.gruppeo.wise2122_java_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableJpaRepositories(basePackages = "de.gruppeo.wise2122_java_server.repository")
@EntityScan(basePackages = "de.gruppeo.wise2122_java_server.model")
@SpringBootApplication
public class MibquizzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(MibquizzzApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/auth/login")
						.allowedOrigins("http://localhost:63342")
						.allowCredentials(true);
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:63342")
						.allowCredentials(true);
			}
		};
	}

}
