package fr.tharbad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TharbadApplication {

	public static void main(String[] args) {
		SpringApplication.run(TharbadApplication.class, args);
	}

}
