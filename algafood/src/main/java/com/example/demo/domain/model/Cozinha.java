package com.example.demo.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Setter;

@Data // trás getter e satters
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // gerar hascode e equals direto na var 
public class Cozinha {

	@EqualsAndHashCode.Include 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO ENCREMENTO DE ID
	private Long id;
	
	private String nome;
	
	
}
