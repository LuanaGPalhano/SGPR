package org.example.repository;

import org.example.model.Refeicoes;
import org.springframework.data.jpa.repository.*;
import java.util.List;


public interface RefeicoesRepository extends JpaRepository<Refeicoes, Long> {
    List<Refeicoes> findByConjuntoRefeicoes(String conjuntoRefeicoes);
}
