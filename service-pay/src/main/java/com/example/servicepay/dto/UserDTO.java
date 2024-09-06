package com.example.servicepay.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
    private String name;

	@JsonProperty("email")
    private String email;
	
	@JsonProperty("password")
    private String password;
	
	@JsonProperty("password_confirmed")
    private String passwordConfirmed;
	
	@JsonProperty("admin")
    private boolean admin;
}
