package school.sptech.iara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IaraApplication {

	public static void main(String[] args) {
		SpringApplication.run(IaraApplication.class, args);
	}
}
