package com.example.servicepay.dto;


import org.springframework.hateoas.RepresentationModel;

import com.example.servicepay.entities.Endereco;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDto extends RepresentationModel<EnderecoDto>{

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
