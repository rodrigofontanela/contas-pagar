package com.contas.pagar.repository;

import com.contas.pagar.model.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long> {

}
