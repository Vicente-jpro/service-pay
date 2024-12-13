package com.example.servicepay.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class AuthMessageDTO {
	@JsonProperty("message")
	private String message;
}
