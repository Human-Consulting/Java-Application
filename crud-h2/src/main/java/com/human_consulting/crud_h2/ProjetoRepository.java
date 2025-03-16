package com.human_consulting.crud_h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    Boolean existsByDescricaoContainingIgnoreCase(String descricao);
}
