package com.example.servicepay.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProvinciaDTO {
	private Long id;
	private String nomeProvincia;
	//private List<MunicipioDTO> municipiosDto;

}
