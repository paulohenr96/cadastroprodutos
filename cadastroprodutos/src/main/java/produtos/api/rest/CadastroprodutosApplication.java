package produtos.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CadastroprodutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroprodutosApplication.class, args);
		
//		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
