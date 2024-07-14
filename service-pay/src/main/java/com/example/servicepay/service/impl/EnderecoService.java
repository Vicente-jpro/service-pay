package com.example.servicepay.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.servicepay.domain.entities.Endereco;
import com.example.servicepay.domain.entities.Municipio;
import com.example.servicepay.domain.repositories.EnderecoRepository;
import com.example.servicepay.exceptions.EnderecoException;
import com.example.servicepay.rest.controllers.MunicipioController;
import com.example.servicepay.rest.dto.EnderecoRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EnderecoService {

	private final EnderecoRepository enderecoRepository;
	private final ModelMapper modelMapper;
	
	public Endereco salvar(EnderecoRequestDto enderecoRequestDTO) {
		log.info("Salvando o endereco...");
		
		Endereco endereco = modelMapper.map(enderecoRequestDTO, Endereco.class);
		Municipio municipio = modelMapper.map(enderecoRequestDTO.getMunicipio(), Municipio.class);
		endereco.setMunicipio(municipio);
		
		return enderecoRepository.save(endereco);
	}
	
	public Endereco atualizar(EnderecoRequestDto enderecoRequestDTO, Long idEndereco) {
		log.info("Atualizando o endereco...");		
		Endereco enderecoSalvo = getEnderecoById(idEndereco);
		enderecoRequestDTO.setId(enderecoSalvo.getId());
		
		return this.salvar(enderecoRequestDTO);
	}
	
	public Endereco getEnderecoById(Long idEndereco) {
		log.info("Buscando o endereco co ID: {}", idEndereco);
		
		Endereco endereco = enderecoRepository.findById(idEndereco).get();
		if(endereco == null) {
			log.error("Endereco nao encontrado ID: {}", idEndereco);
			throw new EnderecoException("Endereco nao encontrado.");
		}
		
		return endereco;
	}
	

	
}
