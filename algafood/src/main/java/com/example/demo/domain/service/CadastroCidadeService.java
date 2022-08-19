package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	public void excluir (Long cidadeId) {
      try {
    	  cidadeRepository.deleteById(cidadeId);
          } catch (EmptyResultDataAccessException erro) {
    		//throw é um statement q manda a exceção ser lançada
			throw new EntidadeNaoEncontradaException(
	     	String.format("Não existe um cadastro de cidade com o codigo %d", cidadeId));
	      }  
      catch (DataIntegrityViolationException erro) {
    	    throw new EntidadeEmUsoException(
    	    String.format("Cidade de código %d não pode ser removida, pois está em uso", cidadeId));
	      }

       }
}
