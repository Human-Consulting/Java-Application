package com.pi.crud_h2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Boolean existsByEmail(String email);
}
