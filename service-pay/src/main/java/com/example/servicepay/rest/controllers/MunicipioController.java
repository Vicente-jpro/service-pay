package com.example.servicepay.rest.controllers;

import java.util.ArrayList;
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

import com.example.servicepay.domain.entities.Municipio;
import com.example.servicepay.rest.dto.MunicipioDTO;
import com.example.servicepay.rest.dto.ProvinciaDTO;
import com.example.servicepay.service.impl.MunicipioService;

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
	
	@GetMapping("/{id_provincia}")
	@ApiOperation("Buscar todos os Municipios pelo id_provincia.")
	@ApiResponse(code = 200, message = "Operacao de busca de Municipios realizada com sucesso.")
	@ResponseStatus(HttpStatus.OK)
	public List<MunicipioDTO> getMunicipiosByProvincia(@PathVariable("id_provincia") Long idProvincia){
		
		List<Municipio> municipios = municipioService.getMunicipiosByProvincia(idProvincia);
	//	List<MunicipioDTO> municipiosDTO = new ArrayList<>();
		/*
		for(Municipio mp: municipios) {
			ProvinciaDTO provinciaDTO = modelMapper.map(mp.getProvincia(), ProvinciaDTO.class);
			
			MunicipioDTO pDto = MunicipioDTO
					.builder()
						.id(mp.getId())
						.nomeMunicipio(mp.getNomeMunicipio())
						.provincia(provinciaDTO)
					.build();
			municipiosDTO.add(pDto);
		}
		*/
		
		
		return municipios.stream().map( m ->{
			
			MunicipioDTO municipiosDTO = modelMapper.map(m, MunicipioDTO.class);
	
			return municipiosDTO;		
		}).collect(Collectors.toList());
		
	//	return municipiosDTO;
	}
}
