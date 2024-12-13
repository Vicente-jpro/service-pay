package com.example.servicepay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enderecos_cliente")
public class EnderecoCliente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "morada1", length = 100)
	private String morada1;

	@Column(name = "morada2", length = 100)
	private String morada2;
	
	@Column(name = "telemovel1", length = 20, unique = true, updatable = true)
	private String telemovel1;
	
	@Column(name = "telemovel2", length = 20, unique = true, updatable = true)
	private String telemovel2;
	
	@ManyToOne
	@JoinColumn(name = "municipio_id")
	private Municipio municipio;
	
	@OneToOne
	@JoinColumn(name = "cliente_id", referencedColumnName = "id")
	private Cliente cliente;
	

}
