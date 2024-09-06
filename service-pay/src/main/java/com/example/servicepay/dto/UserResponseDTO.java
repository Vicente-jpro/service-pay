package com.example.servicepay.dto;


import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
    private String name;

	@JsonProperty("email")
    private String email;
}
