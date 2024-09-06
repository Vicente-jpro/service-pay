package com.example.servicepay.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.servicepay.entities.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>{

	List<Municipio> findByProvinciaId(@Param("provincia") Long idProvincia); 
}
