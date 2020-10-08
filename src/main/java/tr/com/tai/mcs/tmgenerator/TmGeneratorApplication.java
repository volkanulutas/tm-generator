package tr.com.tai.mcs.tmgenerator;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class TmGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmGeneratorApplication.class, args);
	}
}
