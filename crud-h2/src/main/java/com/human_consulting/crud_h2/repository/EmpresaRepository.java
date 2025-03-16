package com.human_consulting.crud_h2.repository;

import com.human_consulting.crud_h2.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    Boolean existsByCnpj(String cnpj);
}
