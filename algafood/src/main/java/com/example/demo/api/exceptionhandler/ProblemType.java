package com.example.demo.api.exceptionhandler;

import lombok.Getter;

//tipo do problema
@Getter
public enum ProblemType {
	
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"), ;       
	
	private String title;
	
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://www.youtube.com/watch?v=M55eHQrqGBs&ab_channel=flosqui" + path;
		this.title = title;
	}

}
