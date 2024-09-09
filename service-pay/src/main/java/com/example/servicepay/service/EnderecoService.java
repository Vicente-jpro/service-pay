package com.example.servicepay.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.servicepay.dto.EnderecoDto;
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
	private final MunicipioService municipioService;
	private final ProvinciaService provinciaService;
	private final ModelMapper modelMapper;
	
	public Endereco salvar(EnderecoDto enderecoDTO, UserModel usuario) {
		log.info("Salvando o endereco...");
		
		Endereco endereco = modelMapper.map(enderecoDTO, Endereco.class);
		Municipio municipio = municipioService.findById(enderecoDTO.getMunicipio().getId());
		endereco.setUser(usuario);
		endereco.setMunicipio(municipio);
		
		return enderecoRepository.save(endereco);
	}
	
	public Endereco atualizar(EnderecoDto enderecoDTO, UserModel user) {
		log.info("Atualizando o endereco...");		
		Endereco enderecoSalvo = findByUser(user);
		enderecoDTO.setId(enderecoSalvo.getId());
		
		return this.salvar(enderecoDTO, user);
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
