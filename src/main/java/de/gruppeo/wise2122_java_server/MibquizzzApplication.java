package de.gruppeo.wise2122_java_server;

import org.jetbrains.annotations.NotNull;
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
			public void addCorsMappings(@NotNull CorsRegistry registry) {

				registry.addMapping("/**")
						.allowedOrigins("http://localhost")
						.allowCredentials(true)
						.allowedMethods("GET", "PUT", "POST", "OPTIONS")
						.allowedHeaders("*");

			}
		};
	}

}
