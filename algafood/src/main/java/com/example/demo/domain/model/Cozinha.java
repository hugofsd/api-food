package com.example.demo.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Setter;

@Data // trás getter e satters
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // gerar hascode e equals direto na var 
public class Cozinha {

	@NotNull(groups = Groups.CadastroRestaurante.class)
	@EqualsAndHashCode.Include 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO ENCREMENTO DE ID
	private Long id;
	
	@NotBlank 
	@Column(nullable = false) //nullable : não aceita nullo
	private String nome;
	
	
	@JsonIgnore // ignorar essa classe ao fazer consultas
	@OneToMany(mappedBy = "cozinha") // uma para muitos, uma cozinha pode ter muitos restaurantes
	private List<Restaurante> restaurantes = new ArrayList<>();
	
	
}
