package com.pi.crud_h2.entrega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {
    Boolean existsByDescricaoContainingIgnoreCase(String descricao);
}
