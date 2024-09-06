package com.example.servicepay.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "municipios")
public class Municipio implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome_municipio", length = 50, unique = true)
	private String nomeMunicipio;
	
	@ManyToOne
	@JoinColumn(name = "provincia_id")
	private Provincia provincia;
	
	@JsonIgnore
	@OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Endereco> endereco;
}
