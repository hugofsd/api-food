package com.example.demo;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Test; // solução de tutor
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // para levantar servidor web
public class CadastroCozinhaIT {

	@LocalServerPort
	private int port; // recebe a nova porta para testes
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		given()
			.basePath("/cozinhas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	

}
