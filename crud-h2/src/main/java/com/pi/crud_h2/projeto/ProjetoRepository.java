package com.pi.crud_h2.projeto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    Boolean existsByDescricaoContainingIgnoreCase(String descricao);
}
