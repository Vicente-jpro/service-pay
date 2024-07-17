package com.example.servicepay.rest.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicepay.domain.entities.Endereco;
import com.example.servicepay.domain.entities.Municipio;
import com.example.servicepay.exceptions.EnderecoException;
import com.example.servicepay.rest.dto.EnderecoRequestDto;
import com.example.servicepay.rest.dto.EnderecoResponseDto;
import com.example.servicepay.service.impl.EnderecoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

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
	public Endereco atualizar(@RequestBody EnderecoRequestDto enderecoRequestDTO, @PathVarible("id_endereco") Long idEndereco) {
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
