package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.salvar(estado);
	}
	
	public void excluir (Long estadoId) {
		try {
			estadoRepository.remover(estadoId);
		} catch(EmptyResultDataAccessException erro) {
			//throw é um statement q manda a exceção ser lançada
			throw new EntidadeNaoEncontradaException(
	     	String.format("Não existe um cadastro de estado com o codigo %d", estadoId));
			
		} catch(DataIntegrityViolationException erro) {
			throw new EntidadeEmUsoException(
			String.format("Estado de código %d não pode ser removida, pois está em uso", estadoId));
		}
	}

}
