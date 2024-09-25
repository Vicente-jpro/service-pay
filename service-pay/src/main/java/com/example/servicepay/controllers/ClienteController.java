package com.example.servicepay.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicepay.dto.ClienteEnderecoDTO;
import com.example.servicepay.entities.Cliente;
import com.example.servicepay.service.ClienteService;
import com.example.servicepay.util.SelfLinkHateoas;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api("ClienteController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;
	private final ModelMapper modelMapper;
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
		@ApiResponse(code = 404, message = "Nao foi possivel salvar o cliente.")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteEnderecoDTO salvar(@RequestBody ClienteEnderecoDTO clienteDTO) {
		
		Cliente cliente = clienteService.salvar(clienteDTO);
		ClienteEnderecoDTO client = modelMapper.map(cliente, ClienteEnderecoDTO.class);
		
		Link link = SelfLinkHateoas.getLink(ClienteEnderecoDTO.class, client.getId());
		client.add(link);
		
		return client;
		
	}
	@GetMapping(path = "/{id_cliente}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente achado com sucesso."),
		@ApiResponse(code = 404, message = "Cliente nao foi encontrado.")
	})
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Buscar cliente pelo id.")
	public ClienteEnderecoDTO findById(@PathVariable("id_cliente") Long id) {
		
		Cliente cliente = clienteService.findById(id);
		ClienteEnderecoDTO clienteDTO = modelMapper.map(cliente, ClienteEnderecoDTO.class);
		Link link = SelfLinkHateoas.getLink(ClienteEnderecoDTO.class, id);
		clienteDTO.add(link);
		
		return clienteDTO;
	}
	
	@GetMapping(path = "/{id_cliente}/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cliente achado com sucesso."),
		@ApiResponse(code = 404, message = "Cliente nao foi encontrado.")
	})
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Buscar cliente pelo id.")
	public ClienteEnderecoDTO findByEnderecoCliente(@PathVariable("id_cliente") Long id) {
		
		Cliente cliente = clienteService.findByEnderecoCliente(id);
		ClienteEnderecoDTO clienteDTO = modelMapper.map(cliente, ClienteEnderecoDTO.class);
		Link link = SelfLinkHateoas.getLink(ClienteEnderecoDTO.class, id);
		clienteDTO.add(link);
		
		return clienteDTO;
	}

}



	
	
	