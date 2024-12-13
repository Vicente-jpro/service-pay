package com.example.servicepay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.servicepay.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query(value ="select * from clientes "
			+ "left join enderecos_cliente "
			+ "on enderecos_cliente.cliente_id = clientes.id "
			+ "where clientes.id = :id_cliente", 
	nativeQuery = true)
	Cliente findClienteWithEndereco(@Param("id_cliente") Long idCliente);
	
}
