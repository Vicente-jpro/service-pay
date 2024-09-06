package com.example.servicepay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.servicepay.entities.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{

}
