package com.example.servicepay.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "Provincias") 
public class Provincia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column( name = "id")
	private Long id;
	
	@Column( name = "nome_provincia", length = 50, unique = true)
	private String nomeProvincia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Municipio> municipios = new ArrayList<>();

}
