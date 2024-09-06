package com.example.servicepay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicepay.entities.Endereco;
import com.example.servicepay.entities.UserModel;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	Endereco findByUser(UserModel user);
}
