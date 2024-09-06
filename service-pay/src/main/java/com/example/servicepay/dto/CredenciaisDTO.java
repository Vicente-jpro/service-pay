package com.example.servicepay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredenciaisDTO {
	
	@JsonProperty("email")
    private String email;
    
	@JsonProperty("password")
	private String password;
}
