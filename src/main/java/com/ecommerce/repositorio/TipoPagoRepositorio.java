package com.ecommerce.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.modelo.TipoPago;

@Repository
public interface TipoPagoRepositorio extends JpaRepository<TipoPago, Long>{

}
