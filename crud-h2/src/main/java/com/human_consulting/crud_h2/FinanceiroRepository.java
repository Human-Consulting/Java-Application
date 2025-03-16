package com.human_consulting.crud_h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceiroRepository extends JpaRepository<Financeiro, Integer> {
}
