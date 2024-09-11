package com.example.servicepay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "nome_cliente", "email_cliente", "endereco_cliente"})
public class ClienteDTO {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome_cliente")
	private String nome;
	
	@JsonProperty("email_cliente")
	private String email;
	
	@JsonProperty("endereco_cliente")
    private EnderecoClienteDTO enderecoCliente;
}
