package com.example.servicepay.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "login", unique = true)
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;
    
    @Column(name = "senha")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;
    
    @Column(name = "admin")
    private boolean admin;

}
