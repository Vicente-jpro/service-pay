package com.example.servicepay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicepay.entities.Atividade;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{

}
