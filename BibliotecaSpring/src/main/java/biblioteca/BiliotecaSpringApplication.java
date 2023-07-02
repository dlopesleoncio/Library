package biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("biblioteca")
public class BiliotecaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiliotecaSpringApplication.class, args);
	}

}
