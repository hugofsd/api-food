package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.exception.CozinhaNaoEncontradaException;
import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.model.Cozinha;
import com.example.demo.domain.service.CadastroCozinhaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		//cenario
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		//ação
		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
		
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	//exemplo que deu certo
	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
	  assertThrows(ConstraintViolationException.class, () -> {
	    Cozinha novaCozinha = new Cozinha();
	    novaCozinha.setNome(null);
	    novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
	  });
	}
	


	

}
