package org.example.DTO;

import java.time.LocalDate;
import java.util.List;

public record DietaResponse(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        String objetivo,
        Long nutricionistaId,
        Long pacienteId,
        List<RefeicaoResponse> refeicoes
) {}