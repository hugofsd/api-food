package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@Column(nullable = false) //nullable : não aceita nullo
	private String nome;
	
	
	@Column(name="taxa_frete", nullable = false) // nome da coluna
	private BigDecimal taxaFrete;
	
	@ManyToOne // muitos restaurantes tem 1 cozinha
	@JoinColumn(name="cozinha_id", nullable = false) // nome da coluna
	private Cozinha cozinha;
	
	

	
	
	

}
