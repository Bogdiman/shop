package ro.msg.learning.shop;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:h2:file:~/test", "sa", null);
		flyway.migrate();

		SpringApplication.run(ShopApplication.class, args);
	}
}
