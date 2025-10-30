package org.example.DTO;

public record ItemRefeicaoResponse(
        Long id,
        String alimento,
        double quantidade,
        String unidadeMedida,
        String resumoNutricional
) {}

