package com.example.servicepay.rest.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicepay.domain.entities.Endereco;
import com.example.servicepay.domain.entities.Municipio;
import com.example.servicepay.domain.repositories.EnderecoRepository;
import com.example.servicepay.exceptions.EnderecoException;
import com.example.servicepay.rest.dto.EnderecoRequestDto;
import com.example.servicepay.rest.dto.EnderecoResponseDto;
import com.example.servicepay.service.impl.EnderecoService;

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
	
	@PostMapping
	@ApiOperation("Salvar endereco")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(code = 201, message = "Contacto salvo com sucesso."),
		@ApiResponse(code = 404, message = "Erro ao salvar o contacto." )
	})
	public EnderecoResponseDto salvar(@RequestBody EnderecoRequestDto enderecoRequestDTO) {

		Endereco endereco = enderecoService.salvar(enderecoRequestDTO);
		EnderecoResponseDto enderecoResponseDTO = modelMapper.map(endereco, EnderecoResponseDto.class);
		
		return enderecoResponseDTO;
	}
	
	@PatchMapping("/{id_endereco}")
	@ApiOperation("Atualizar endereco pedo id.")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(code = 201, message = "Contacto salvo com sucesso."),
		@ApiResponse(code = 404, message = "Erro ao salvar o contacto.")
	})
	public EnderecoResponseDto atualizar(@RequestBody EnderecoRequestDto enderecoRequestDTO, @PathVariable("id_endereco") Long idEndereco) {	
		Endereco enderecoSalvo = this.enderecoService.atualizar(enderecoRequestDTO, idEndereco);
		EnderecoResponseDto enderecoResponseDto = modelMapper.map(enderecoSalvo, EnderecoResponseDto.class);
		return enderecoResponseDto;
	}
	
	@GetMapping("/{id_endereco}")
	@ApiOperation("Buscar endereco pelo ID.")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Endereco encontrado com sucesso."), 
		@ApiResponse(code = 404, message = "Parametro invalido")
	})
	public EnderecoResponseDto getEnderecoById(Long idEndereco) {
		
		Endereco endereco = this.enderecoService.getEnderecoById(idEndereco);
		EnderecoResponseDto enderecoResponseDto = this.modelMapper.map(endereco, EnderecoResponseDto.class);
		return enderecoResponseDto;
	}
	
	
	
}
