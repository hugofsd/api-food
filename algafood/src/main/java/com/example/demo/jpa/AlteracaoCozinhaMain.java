package com.example.demo.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.example.demo.AlgafoodApplication;
import com.example.demo.domain.model.Cozinha;

public class AlteracaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
	Cozinha cozinha = new Cozinha();
	cozinha.setId(1L); //atribuir id
	cozinha.setNome("Brasileira edit");
	
    cadastroCozinha.adicionar(cozinha);
}
}
