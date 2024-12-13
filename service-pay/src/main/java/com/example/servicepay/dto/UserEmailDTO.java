package com.example.servicepay.dto;


import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailDTO {
	
	@JsonProperty("email")
	@NotBlank(message = "Field Email can not be blank.")
	private String email; 
}
