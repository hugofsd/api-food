package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // trás getter e satters
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // gerar hascode e equals direto na var 
@Entity
public class Restaurante {
	
	@EqualsAndHashCode.Include 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO ENCREMENTO DE ID
	private Long id;
	
	@NotNull
	@Column(nullable = false) //nullable : não aceita nullo
	private String nome;
	
	
	@Column(name="taxa_frete", nullable = false) // nome da coluna
	private BigDecimal taxaFrete;
	
	@JsonIgnore
	@Embedded // tipo encorporado
	private Endereco endereco;
	
	@JsonIgnore
	@CreationTimestamp //criar data automaticamente
	@Column(nullable = false, columnDefinition = "datetime") //obrigatório, 
	private LocalDateTime dataCadastro;
	//datetime para tirar seg do bd
	 
	@JsonIgnore
	@UpdateTimestamp //data hora atual sempre q atualizado
	@Column(nullable = false, columnDefinition = "datetime") //obrigatório
	private LocalDateTime dataAtualizacao;
	
	// muitos restaurantes tem 1 cozinha
	//@JsonIgnoreProperties("hibernateLazyInitializer") //ignorar uma propriedade da cozinha
	//@JsonIgnore  //ignorar a cozinha
	@ManyToOne ///(fetch = FetchType.LAZY) //Carregar apenas quando precisar, n fazer select
	@JoinColumn(name="cozinha_id", nullable = false) // nome da coluna
	private Cozinha cozinha;
	 
	@JsonIgnore //ocultar informação de Get
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();;
	//  erro Geração automática de DDL 
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();       

}
