package com.example.demo.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // trás getter e satters
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // gerar hascode e equals direto na var 
public class Estado {
	
	@EqualsAndHashCode.Include 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO ENCREMENTO DE ID
	private Long id;
	
	@Column(nullable = false) 
	private String nome;

}
