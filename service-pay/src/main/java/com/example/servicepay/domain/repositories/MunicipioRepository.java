package com.example.servicepay.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.servicepay.domain.entities.Municipio;
import com.example.servicepay.domain.entities.Provincia;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>{

	List<Municipio> findByProvinciaId(@Param("provincia") Long idProvincia); 
}
