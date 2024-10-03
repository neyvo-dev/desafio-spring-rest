package com.neyvo.desafioSpringBoot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Desafio Spring Rest", version = "1.0", description = "API para gerenciamento de produtos"),
		servers = {
				@Server(
						description = "Local",
						url = "http://localhost:8080/"
				)
		}
)
public class DesafioSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSpringBootApplication.class, args);
	}

}
