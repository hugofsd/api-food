package com.example.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{

	 //METODOS DE CONSULTA SALVOS EM PASTA DOC
	// depois do by vem os criterios, Containing depois do nome da var para localziar por letra
	List<Cozinha> findByNomeContaining(String nome);
	
	Optional<Cozinha> findUnicoByNome(String nome);
	
	//existencia do nome por true ou false
	boolean existsByNome(String nome);
	
  
	
} 
