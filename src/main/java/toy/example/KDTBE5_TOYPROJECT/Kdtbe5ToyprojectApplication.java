package toy.example.KDTBE5_TOYPROJECT;

import db.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kdtbe5ToyprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Kdtbe5ToyprojectApplication.class, args);
		DBConnection.getInstance();
	}

}
