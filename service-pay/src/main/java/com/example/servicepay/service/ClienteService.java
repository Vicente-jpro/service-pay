package com.example.servicepay.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.servicepay.dto.ClienteEnderecoDTO;
import com.example.servicepay.entities.Cliente;
import com.example.servicepay.entities.EnderecoCliente;
import com.example.servicepay.entities.Municipio;
import com.example.servicepay.exceptions.ClienteException;
import com.example.servicepay.repositories.ClienteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {
	
	private final ModelMapper modelMapper;
	private final MunicipioService municipioService;
	private final ClienteRepository clienteRepository;
	
	public Cliente salvar(ClienteEnderecoDTO clienteDTO) {
		log.info("Salvando o cliente...");
		Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
		Municipio municipio = municipioService.findById(clienteDTO.getEnderecoCliente().getMunicipio().getId());
		
		EnderecoCliente enderecoCliente = modelMapper.map(clienteDTO.getEnderecoCliente(), EnderecoCliente.class);
		enderecoCliente.setMunicipio(municipio);
		cliente.setEnderecoCliente(enderecoCliente);
		
		return clienteRepository.save(cliente);
	}
	
	
	public Cliente findById(Long idCliente) {
		log.info("Buscando o cliente com ID: {}", idCliente);
		
		Cliente cliente = this.clienteRepository.findById(idCliente).get();
		if (cliente != null)
			return cliente;
		
		log.error("Cliente nao foi encontrado. ID invalido: {}", idCliente);
		throw new ClienteException("Cliente nao foi encontrado. ID invalido: "+idCliente);
		
	}
	
	public Cliente findClienteWithEndereco(Long idCliente) {
		log.info("Buscando o cliente e seu endereco com ID: {}", idCliente);
		
		Cliente cliente = this.clienteRepository.findClienteWithEndereco(idCliente);
		if (cliente != null)
			return cliente;
		
		log.error("Cliente nao foi encontrado. ID invalido: {}", idCliente);
		throw new ClienteException("Cliente nao foi encontrado. ID invalido: "+idCliente);
		
	}
	
	
	public Cliente atualizar(ClienteEnderecoDTO clienteDTO, Long idCliente) {
		log.info("Atualizando o cliente com ID: {}", idCliente);
		Cliente cliente = this.findById(idCliente);
		clienteDTO.setId(cliente.getId());
		
		return this.salvar(clienteDTO);
	}
	
	public void eliminar(Long idCliente) {
		log.info("Eliminando o cliente com ID: {}", idCliente);
		
		Cliente cliente = this.findById(idCliente);
		this.clienteRepository.delete(cliente);
	}
}
