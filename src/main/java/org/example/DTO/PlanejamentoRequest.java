package org.example.DTO;

import java.util.List;

public record PlanejamentoRequest(
    String descricao,
    List<EntradaRequest> entradas
){
    public record EntradaRequest(
        String dia,
        String refeicao,
        List<PorcaoRequest> porcao
    ){}
    public record PorcaoRequest(
        String alimento,
        String quantidade
    ){}
}