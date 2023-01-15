package com.example.demo.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

//classe rersponsavel por capturar as exeções do projeto
@ControllerAdvice
public class ApiExceptionhandler extends ResponseEntityExceptionHandler {
	
	
	//handleHttpMessageNotWri + ctrl / space
	//metodo para tratar erro 400 em caso de corpo errado em put por ex...
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		//Throwable:tipo de exception(super). rootCause=causaraiz
		Throwable rootCause = ExceptionUtils.getRootCause(ex); //causa raiz do erro
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause,
					headers, status, request);
		}
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPRIEENSIVEL;
		
		String datail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		
		Problem problem = createProblemBuilder(status, problemType, datail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), 
				status, request);
	}
	

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		//ref:refernecuia
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining(".")); 
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPRIEENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}


	//ResponseEntityExceptionHandler: tratar varias exceptions ao mesmo tempo
	
	//metodo para tratamento de mensagem somente para mensagem
	//ExceptionHandler: aceita o argumento(classe que eu quero tratar
	@ExceptionHandler(EntidadeNaoEncontradaException.class)

	//tratar
	public ResponseEntity<?> hundleEntidadeNaoEncontradaException(
			EntidadeNaoEncontradaException ex, WebRequest request) {
		
		HttpStatus status =  HttpStatus.NOT_FOUND;
		
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		
		String datail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, datail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), 
				status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUsoException(
			EntidadeEmUsoException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		
		String datail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, datail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), 
				status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException ex, WebRequest request) {
	
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		
		String datail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, datail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), 
				status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		//Exception sem passar o corpo
		if (body == null) {
			body = Problem.builder()
			.title(status.getReasonPhrase())
			.status(status.value())
			.build();
		} else if (body instanceof String) {
			body = Problem.builder()
			.title((String) body)
			.status(status.value())
			.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
			ProblemType problemType, String detail){
		return Problem.builder()
			.status(status.value())
			.type(problemType.getUri())
			.title(problemType.getTitle())
			.detail(detail);
	}
	
}
