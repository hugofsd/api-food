package com.example.demo.api.exceptionhandler;

import lombok.Getter;

//tipo do problema
@Getter
public enum ProblemType {
	
	MENSAGEM_INCOMPRIEENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");       
	
	private String title;
	
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://www.youtube.com/watch?v=M55eHQrqGBs&ab_channel=flosqui" + path;
		this.title = title;
	}

}
