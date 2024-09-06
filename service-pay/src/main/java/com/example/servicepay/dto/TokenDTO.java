package com.example.servicepay.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
	@JsonProperty("email")
    private String email;
	
	@JsonProperty("token")
    private String token;
}
