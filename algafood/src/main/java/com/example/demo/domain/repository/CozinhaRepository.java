package com.example.demo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{


	// depois do by vem os criterios
	List<Cozinha> findByNome(String nome);
	
	Optional<Cozinha> findUnicoByNome(String nome);
	

} 
