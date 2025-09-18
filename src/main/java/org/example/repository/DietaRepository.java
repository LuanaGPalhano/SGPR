package org.example.repository;

import org.example.model.Dieta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;   

import java.util.Optional;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Long> {
    Optional<Dieta> findByPacienteId(Long pacienteId);
}