package com.example.servicepay.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioProvinciaDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome_municipio")
	private String nomeMunicipio;
	
	@JsonProperty("provincia")
	private ProvinciaDTO provincia;
}
