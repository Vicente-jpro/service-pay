package com.example.servicepay.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicepay.dto.MunicipioDTO;
import com.example.servicepay.dto.MunicipioProvinciaDTO;
import com.example.servicepay.entities.Municipio;
import com.example.servicepay.service.MunicipioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/municipios")
public class MunicipioController {

	private final MunicipioService municipioService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(path = "/{id_provincia}/provincia",  produces = "application/json", consumes = "application/json")
	@ApiOperation("Buscar todos os Municipios pelo id_provincia.")
	@ApiResponse(code = 200, message = "Operacao de busca de Municipios realizada com sucesso.")
	@ResponseStatus(HttpStatus.OK)
	public List<MunicipioProvinciaDTO> getMunicipiosAndProvinciaId(@PathVariable("id_provincia") Long idProvincia){
		
		List<Municipio> municipios = municipioService.getMunicipiosAndProvinciaId(idProvincia);
		
		return municipios.stream().map( m ->{
			
			MunicipioProvinciaDTO municipiosDTO = modelMapper.map(m, MunicipioProvinciaDTO.class);
	
			return municipiosDTO;		
		}).collect(Collectors.toList());
		

	}
	
	@GetMapping(path = "/{provincia_id}", produces = "application/json")
	@ApiOperation("Buscar Municipios pela provincia_id")
	@ApiResponse(code = 200, message = "Busca realizada com sucesso")
	@ResponseStatus(HttpStatus.OK)
	public List<MunicipioDTO> getMunicipiosByProvincia(@PathVariable("provincia_id") Long idProvincia){
		List<Municipio> municipios = municipioService.getMunicipiosAndProvinciaId(idProvincia);
		
		return municipios.stream().map( mp ->{
			
			MunicipioDTO municipioDTO = modelMapper.map(mp, MunicipioDTO.class);
			return municipioDTO;
			
		}).collect(Collectors.toList());
	}
}
