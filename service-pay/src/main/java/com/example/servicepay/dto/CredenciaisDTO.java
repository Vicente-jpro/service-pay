package com.example.servicepay.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CredenciaisDTO {
	

	@JsonProperty("email")
	@NotBlank(message = "Field Email can not be blank.")
    private String email;
    
	@JsonProperty("password")
	@NotBlank(message = "Field Password can not be blank.")
	private String password;
}
