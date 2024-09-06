package com.example.servicepay.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.servicepay.dto.EnderecoRequestDto;
import com.example.servicepay.entities.Endereco;
import com.example.servicepay.entities.Municipio;
import com.example.servicepay.entities.UserModel;
import com.example.servicepay.exceptions.EnderecoException;
import com.example.servicepay.repositories.EnderecoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EnderecoService {

	private final EnderecoRepository enderecoRepository;
	private final ModelMapper modelMapper;
	
	public Endereco salvar(EnderecoRequestDto enderecoRequestDTO, UserModel usuario) {
		log.info("Salvando o endereco...");
		
		Endereco endereco = modelMapper.map(enderecoRequestDTO, Endereco.class);
		Municipio municipio = modelMapper.map(enderecoRequestDTO.getMunicipio(), Municipio.class);
		endereco.setUser(usuario);
		endereco.setMunicipio(municipio);
		
		return enderecoRepository.save(endereco);
	}
	
	public Endereco atualizar(EnderecoRequestDto enderecoRequestDTO, UserModel user) {
		log.info("Atualizando o endereco...");		
		Endereco enderecoSalvo = findByUser(user);
		enderecoRequestDTO.setId(enderecoSalvo.getId());
		
		return this.salvar(enderecoRequestDTO, user);
	}
	
	public Endereco findByUser( UserModel user) {
		log.info("Buscando o endereco co ID: {}", user.getId());
		
		Endereco endereco = enderecoRepository.findByUser(user);
		if(endereco == null) {
			log.error("Endereco nao encontrado ID: {}", user.getId());
			throw new EnderecoException("Endereco nao encontrado.");
		}
		
		return endereco;
	}
	

	
}
