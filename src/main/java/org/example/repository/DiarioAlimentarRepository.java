package org.example.repository;

import org.example.model.DiarioAlimentar;
import org.springframework.data.jpa.repository.*;
import java.util.List;

public interface DiarioAlimentarRepository extends JpaRepository<DiarioAlimentar, Long> {
    List<DiarioAlimentar> findByPacienteId(Long pacienteId);
}
