package com.example.servicepay.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.servicepay.entities.Municipio;
import com.example.servicepay.entities.Provincia;
import com.example.servicepay.exceptions.MunicipioException;
import com.example.servicepay.repositories.MunicipioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MunicipioService  {
	
	private final MunicipioRepository municipioRepository;
	
	public List<Municipio> getMunicipiosAndProvinciaId(Long idProvincia) {
		log.info("Buscando todos os municipios correspondente com provincia_id: {}", idProvincia); 
		Provincia  provincia = new Provincia();
		provincia.setId(idProvincia);
		List<Municipio> municiosProvincia = municipioRepository.findByProvinciaId(provincia.getId());
		
		return municiosProvincia;
	}
	
	public Municipio findById(Long idMunicipio) {
		Municipio municipio = municipioRepository.findById(idMunicipio).get();
		if(municipio != null)
			return municipio;
		log.error("Municipio escolhido nao existe. Id invalido");
		throw new MunicipioException("Municipio escolhido nao existe. Id invalido");
	}
	
	
}
