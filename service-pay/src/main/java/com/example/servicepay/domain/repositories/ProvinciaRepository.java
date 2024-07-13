package com.example.servicepay.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicepay.domain.entities.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{

}
