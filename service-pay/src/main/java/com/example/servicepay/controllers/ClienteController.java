package com.example.servicepay.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicepay.dto.ClienteDTO;
import com.example.servicepay.entities.Cliente;
import com.example.servicepay.service.ClienteService;
import com.example.servicepay.util.SelfLinkHateoas;

import io.swagger.annotations.Api;
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
	
	
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cliente salvo com sucesso."),
		@ApiResponse(code = 404, message = "Nao foi possivel salvar o cliente.")
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ClienteDTO salvar(@RequestBody ClienteDTO clienteDTO) {
		
		Cliente cliente = clienteService.salvar(clienteDTO);
		ClienteDTO client = modelMapper.map(cliente, ClienteDTO.class);
		
		Link link = SelfLinkHateoas.getLink(ClienteDTO.class, client.getId());
		client.add(link);
		
		return client;
		
	}
	
	
	public ClienteDTO findById(@PathVariable("id_cliente") Long id) {
		return 
	}
	
	
	
	
	
}
