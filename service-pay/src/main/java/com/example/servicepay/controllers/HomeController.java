package com.example.servicepay.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping
	@PreAuthorize("hasRole('MODERATOR')")
	public String home() {
		return "Hello from home controller.";
	}
}
