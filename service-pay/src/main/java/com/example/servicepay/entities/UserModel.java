package com.example.servicepay.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    @NotEmpty(message = "Username can not be empty.")
    private String name;
    
    @Column(name = "email", unique = true, updatable = true)
    @NotEmpty(message = "email can not be empty.")
    private String email;
    
    @Column(name = "passwrd")
    @NotEmpty(message = "Password can not be empty.")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<Role>();
    
    @Column(name = "activated")
    private boolean activated;
    
    @Column(name = "token_reset_password")
    private String tokenResetPassword;
    
    @Column(name = "token_confirmed_account")
    private String tokenConfirmedAccount;
    
    
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();
    
    
    public boolean isTokenEquals(String token1, String token2) {
    	return token1.equalsIgnoreCase(token2);
    }
    
    public boolean isPasswordEquals(String password1, String password2) {
    	return password1.equalsIgnoreCase(password2);
    }
}
