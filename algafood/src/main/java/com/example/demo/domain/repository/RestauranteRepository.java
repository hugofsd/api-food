package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{

  //Between: entre o valor inicial e final
  List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
  
  //procurar id e(and) nome da cozinha, com filtro no nome por letra.
  List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
  
  //Optional para retorno único
  //First para escolher o pimeiro
  //procurar primeiro restaurante com a letra digitada.
  Optional<Restaurante> findFirstByNomeContaining(String nome);
  
  // Filtrar os primeiros 2 nomes de restaurantes com as palavras digitadas
  List<Restaurante> findTop2ByNomeContaining(String nome);

  //quantos restaurantes por cozinha
  int countByCozinhaId(Long cozinhaId);
	
}
