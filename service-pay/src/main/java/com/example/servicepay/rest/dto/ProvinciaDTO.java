package com.example.servicepay.rest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvinciaDTO {
	private Long id;
	private String nomeProvincia;
	private List<MunicipioDTO> municipiosDto;

}
