package com.example.servicepay.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "Provincias") 
public class Provincia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column( name = "id")
	private Long id;
	
	@Column( name = "nome_pais")
	private String nomePais;
}
