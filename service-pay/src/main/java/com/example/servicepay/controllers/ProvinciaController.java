package com.example.servicepay.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.servicepay.entities.Provincia;
import com.example.servicepay.service.ProvinciaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/provincias")
public class ProvinciaController {

	private final ProvinciaService provinciaService;
	
	@GetMapping
	@ApiOperation("Buscar todas as provincias")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Busca efetuada com sucesso")
	})
	@ResponseStatus(HttpStatus.OK)
	public List<Provincia> getProvincias(){
		return provinciaService.getProvincias();
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation("Buscar provincia pelo id.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Busca da provincia efetuada com sucesso"),
		@ApiResponse(code = 404, message = "Provincia nao encontrada")
	})
	@ResponseStatus(HttpStatus.OK)
	public Provincia getProvinciaById(@PathVariable("id") Long idProvincia) {
		return provinciaService.getProvinciaById(idProvincia);
	}
}
