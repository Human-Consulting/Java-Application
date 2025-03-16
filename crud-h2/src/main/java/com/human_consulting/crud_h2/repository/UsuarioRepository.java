package com.human_consulting.crud_h2.repository;

import com.human_consulting.crud_h2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Boolean existsByEmail(String email);
}
