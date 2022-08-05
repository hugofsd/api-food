package com.example.demo.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.example.demo.AlgafoodApplication;
import com.example.demo.domain.model.Cozinha;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
	Cozinha cozinha1 = new Cozinha();
	cozinha1.setNome("Brasileira");
	
	cadastroCozinha.salvar(cozinha1);

}
}
