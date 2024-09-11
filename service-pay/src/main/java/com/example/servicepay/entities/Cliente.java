package com.example.servicepay.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
