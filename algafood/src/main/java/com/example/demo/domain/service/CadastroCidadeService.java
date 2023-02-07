package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.exception.CidadeNaoEncontradaException;
import com.example.demo.domain.exception.EntidadeEmUsoException;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cidade;
import com.example.demo.domain.model.Estado;
import com.example.demo.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	private static final String  MSG_CIDADE_NAO_ENCONTRADA =
			"Não existe um cadastro de uma cidade com o codigo %d";
	private static final String  MSG_CIDADE_EM_USO =
			"Cidade de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@Transactional
	public Cidade salvar(Cidade cidade) {
		
		Long estadoId = cidade.getEstado().getId(); 
		
		//Buscar estado
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		
//		Estado estado = estadoRepository.findById(estadoId)
//		.orElseThrow(() -> new EntidadeNaoEncontradaException(
//				String.format("Não existe cadastro de estado com código %d", estadoId)));
	
	    cidade.setEstado(estado);
	
		
		return cidadeRepository.save(cidade);
	}
	
	@Transactional 
	public void excluir (Long cidadeId) {
      try {
    	  cidadeRepository.deleteById(cidadeId);
          } catch (EmptyResultDataAccessException erro) {
    		//throw é um statement q manda a exceção ser lançada
			throw new EntidadeNaoEncontradaException(
	     	String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
	      }  
      catch (DataIntegrityViolationException erro) {
           throw new CidadeNaoEncontradaException(cidadeId);
	      }

       }
	
	public Cidade buscarOuFalhar(Long cidadeId) {
	    return cidadeRepository.findById(cidadeId)
	        .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}
	
	
}
