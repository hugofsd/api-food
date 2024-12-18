package com.example.demo.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//só incluir na representação var's q não são nulas
@JsonInclude(Include.NON_NULL)
@Getter
@Builder // padrão de proj para construir obj
public class Problem {
	
	//http da resposta
	private Integer status;
	
	//uri que especifica o tipo do problema
	private String type;
	
	//descrição legivel para as pessoal
	private String title;
	
	// descrição detalhada da ocorrencia do problema
	private String detail; 
	
	// mensagem para o usuario
	private String userMenssage;
	
	// lista de propriedades
	private List<Field> filds;
	
	
	
	@Getter
	@Builder
	public static class Field {
		
		private String name;
		
		private String userMessage;
		
	}
	
}
