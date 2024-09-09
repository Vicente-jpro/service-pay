package com.example.servicepay.controllers;

import java.sql.SQLException;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicepay.dto.EnderecoDto;
import com.example.servicepay.entities.Endereco;
import com.example.servicepay.exceptions.EnderecoException;
import com.example.servicepay.service.EnderecoService;
import com.example.servicepay.util.CurrentUser;
import com.example.servicepay.util.LoggedInUser;
import com.example.servicepay.util.SelfLinkHateoas;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/enderecos")
public class EnderecoController {

	private final EnderecoService enderecoService;
	private final ModelMapper modelMapper;
	
	@PostMapping(path = "/" , produces = "application/json", consumes = "application/json")
	@ApiOperation("Salvar endereco")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(code = 201, message = "Contacto salvo com sucesso."),
		@ApiResponse(code = 404, message = "Erro ao salvar o contacto." )
	})
	public EnderecoDto salvar(@RequestBody EnderecoDto enderecoDTO, 
			@LoggedInUser CurrentUser currentUser) {
		try {

			Endereco endereco = enderecoService.salvar(enderecoDTO, currentUser.getUser());
			EnderecoDto enderecoResponseDTO = modelMapper.map(endereco, EnderecoDto.class);
			
			Link selfLink = SelfLinkHateoas.getLink(EnderecoDto.class, enderecoResponseDTO.getId());
			enderecoResponseDTO.add(selfLink);
			
			return enderecoResponseDTO;
		} catch (Exception e) {
			log.error("Usuario ja possue um endereco ID: "+currentUser.getUser().getId());
			throw new EnderecoException("Usuario ja possue um endereco ");
		}
		
	}
	
	@PatchMapping(path = "/{id_endereco}", produces = "application/json", consumes = "application/json")
	@ApiOperation("Atualizar endereco pedo id.")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(code = 201, message = "Contacto salvo com sucesso."),
		@ApiResponse(code = 404, message = "Erro ao salvar o contacto.")
	})
	public EnderecoDto atualizar(@RequestBody EnderecoDto enderecoRequestDTO, 
			@PathVariable("id_endereco") Long idEndereco,
			@LoggedInUser CurrentUser currentUser) {	
		
		Endereco enderecoSalvo = this.enderecoService.atualizar(enderecoRequestDTO, currentUser.getUser());
		EnderecoDto enderecoResponseDto = modelMapper.map(enderecoSalvo, EnderecoDto.class);
		
		Link selfLink = SelfLinkHateoas.getLink(EnderecoDto.class, enderecoResponseDto.getId());
		enderecoResponseDto.add(selfLink);
		
		return enderecoResponseDto;
	}
	
	
	@GetMapping(path = "/", produces = "application/json")
	@ApiOperation("Buscar endereco pelo Usuario authenticado.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Endereco encontrado com sucesso."), 
		@ApiResponse(code = 404, message = "Parametro invalido")
	})
	public EnderecoDto findByUser(@LoggedInUser CurrentUser currentUser) {
		Endereco endereco = this.enderecoService.findByUser(currentUser.getUser());
		EnderecoDto enderecoResponseDto = this.modelMapper.map(endereco, EnderecoDto.class);

		Link selfLink = SelfLinkHateoas.getLink(EnderecoDto.class, enderecoResponseDto.getId());
		enderecoResponseDto.add(selfLink);
		
		return enderecoResponseDto;
	}
	
	
	
}
