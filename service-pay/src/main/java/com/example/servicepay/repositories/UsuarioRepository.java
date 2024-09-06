package com.example.servicepay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.servicepay.entities.UserModel;

public interface UsuarioRepository extends JpaRepository<UserModel, Integer> {

    Optional<UserModel> findByEmail(String email);
    UserModel findByTokenResetPassword(String token);
    UserModel findByTokenConfirmedAccount(String token);
    
}
