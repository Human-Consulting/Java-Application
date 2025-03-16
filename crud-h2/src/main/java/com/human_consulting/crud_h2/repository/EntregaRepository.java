package com.human_consulting.crud_h2.repository;

import com.human_consulting.crud_h2.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {
    Boolean existsByDescricaoContainingIgnoreCase(String descricao);
}
