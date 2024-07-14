package com.example.servicepay.rest.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.servicepay.domain.entities.Provincia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDTO {

	private Long id;
	private String nomeMunicipio;
	private ProvinciaDTO provinciaDTO;
}
