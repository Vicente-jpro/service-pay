package com.example.servicepay.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDto {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("morada1")
	private String morada1;
	
	@JsonProperty("morada2")
	private String morada2;
	
	@JsonProperty("telemovel1")
	private String telemovel1;
	
	@JsonProperty("telemovel2")
	private String telemovel2;
	
	@JsonProperty("municipio")
	private MunicipioDTO municipio;
}
