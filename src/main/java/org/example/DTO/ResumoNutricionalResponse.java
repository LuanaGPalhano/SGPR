package org.example.DTO;

public record ResumoNutricionalResponse(
    String resumo,
    double calorias_kcal,
    double proteinas_g,
    double carboidratos_g,
    double gorduras_g,
    double fibras_g,
    double sodio_mg,
    String beneficios,
    String observacoes,
    String substituicoes,
    boolean estimativa
)
{}
