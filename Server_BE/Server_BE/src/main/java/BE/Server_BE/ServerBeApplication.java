package BE.Server_BE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ServerBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerBeApplication.class, args);
	}

}
