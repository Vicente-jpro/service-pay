package com.example.servicepay.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clientes")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_cliente", length = 60)
	private String nome;
	
	@Column(name = "email_cliente", length = 60, unique = true, updatable = true)
	private String email;

    @JsonIgnore
    @OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private EnderecoCliente enderecoCliente;
}
