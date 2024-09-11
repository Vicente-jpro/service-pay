package com.example.servicepay.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.servicepay.dto.EnderecoClienteDTO;
import com.example.servicepay.entities.Endereco;
import com.example.servicepay.entities.EnderecoCliente;
import com.example.servicepay.entities.Municipio;
import com.example.servicepay.entities.UserModel;
import com.example.servicepay.exceptions.EnderecoException;
import com.example.servicepay.repositories.EnderecoClienteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EnderecoClienteService {

	private final EnderecoClienteRepository enderecoRepository;
	private final MunicipioService municipioService;
	private final ProvinciaService provinciaService;
	private final ModelMapper modelMapper;
	
	public EnderecoCliente salvar(EnderecoClienteDTO enderecoDTO, UserModel usuario) {
		log.info("Salvando o endereco do cliente...");
		
		EnderecoCliente endereco = modelMapper.map(enderecoDTO, EnderecoCliente.class);
		Municipio municipio = municipioService.findById(enderecoDTO.getMunicipio().getId());
		endereco.setUser(usuario);
		endereco.setMunicipio(municipio);
		
		return enderecoRepository.save(endereco);
	}
	
	public EnderecoCliente atualizar(EnderecoClienteDTO enderecoDTO, Long idEndereco) {
		log.info("Atualizando o endereco do cliente...");		
		Endereco enderecoSalvo = findByUser(user);
		enderecoDTO.setId(enderecoSalvo.getId());
		
		return this.salvar(enderecoDTO, user);
	}
	
	public EnderecoCliente findById(Long idEndereco) {
		log.info("Buscando o endereco do cliente com ID: {}", user.getId());
		
		EnderecoCliente endereco = enderecoRepository.findByUser(user);
		if(endereco == null) {
			log.error("Endereco nao encontrado ID: {}", user.getId());
			throw new EnderecoException("Endereco nao encontrado.");
		}
		
		return endereco;
	}
	

	
}
