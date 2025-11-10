package mk.ukim.finki.wp.wp2025;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Wp2025Application {

	public static void main(String[] args) {
		SpringApplication.run(Wp2025Application.class, args);
	}

}
