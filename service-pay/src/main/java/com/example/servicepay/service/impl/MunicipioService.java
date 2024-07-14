package com.example.servicepay.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.servicepay.domain.entities.Municipio;
import com.example.servicepay.domain.entities.Provincia;
import com.example.servicepay.domain.repositories.MunicipioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MunicipioService  {
	
	private final MunicipioRepository municipioRepository;
	private final ProvinciaService provinciaService;
	
	public List<Municipio> getMunicipiosByProvincia(Long idProvincia) {
		log.info("Buscando todos os municipios correspondente com provincia_id: {}", idProvincia); 
		Provincia  provincia = new Provincia();
		provincia.setId(idProvincia);
		List<Municipio> municiosProvincia = municipioRepository.findByProvincia(provincia);
		
		return municiosProvincia;
	}
	
	
}
