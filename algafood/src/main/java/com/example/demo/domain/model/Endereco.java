package com.example.demo.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable // classe com capacidade de ser incorporada em uma entidade
//(parte de uma entidade)
public class Endereco {

	// nome de coluna por questão de organização 
	
	@Column(name = "endereco_cep")
	private String cep;
	
	@Column(name = "endereco_logradouro")
	private String logradouro;
	
	@Column(name = "endereco_numero")
	private String numero;
	
	@Column(name = "endereco_complemento")
	private String complemento;
	
	@Column(name = "endereco_bairro")
	private String bairro;
	
	@ManyToOne (fetch = FetchType.LAZY)//Carregar apenas quando precisar, n fazer select
	@JoinColumn(name = "endereco_cidade_id")
	private Cidade cidade; 
	
}
