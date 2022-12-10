package com.example.demo.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;

//classe rersponsavel por capturar as exeções do projeto
@ControllerAdvice
public class ApiExceptionhandler extends ResponseEntityExceptionHandler {

	//ResponseEntityExceptionHandler: tratar varias exceptions ao mesmo tempo
	
	//metodo para tratamento de mensagem somente para mensagem
	//ExceptionHandler: aceita o argumento(classe que eu quero tratar
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNãoEncontradoException(
			EntidadeNaoEncontradaException e) {
		
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problema);
	}
	
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(
			EntidadeNaoEncontradaException e) {
		
		Problema problema = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(problema);
	}
	
	//método para tratar EntidadeEmUsoException
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e) {
	    Problema problema = Problema.builder()
	            .dataHora(LocalDateTime.now())
	            .mensagem(e.getMessage()).build();
	    
	    return ResponseEntity.status(HttpStatus.CONFLICT)
	            .body(problema);
	}
	
	
	//handleex.. ctrl+space
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		 body = Problema.builder()
		      .dataHora(LocalDateTime.now())
		      .mensagem(status.getReasonPhrase()) //descrição do status
		      .build();
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
}
