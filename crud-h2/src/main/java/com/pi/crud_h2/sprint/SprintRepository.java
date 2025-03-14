package com.pi.crud_h2.sprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Integer> {
    Boolean existsByDescricaoContainingIgnoreCase(String descricao);
}
