package com.example.servicepay.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioProvinciaDTO {

	private Long id;
	private String nomeMunicipio;
	private ProvinciaDTO provincia;
}
