package com.example.servicepay.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.servicepay.domain.entities.Provincia;
import com.example.servicepay.domain.repositories.ProvinciaRepository;
import com.example.servicepay.exceptions.ProvinciaNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProvinciaService {

	private final ProvinciaRepository provinciaRepository;
	
	public List<Provincia> getProvincias(){
		log.info("Buscando todas as provincias");
		return provinciaRepository.findAll();
	}
	
	public Provincia getProvinciaById(Long idProvincia){
		log.info("Buscando provincia pelo Id: {}", idProvincia);
		
		Provincia provincia =  provinciaRepository.findById(idProvincia).get();
		if (provincia == null) {
			log.error("Provincia nao encontrada. Identificador invalido");
			throw new ProvinciaNotFoundException("Provincia nao encontrada. Identificador invalido");
			
		}
		return provincia;
	}
}
