package org.example.DTO;

import org.example.model.Planejamento;
import org.example.model.EntradaPlanejamento;
import org.example.model.Porcao;

import java.util.List;

public record PlanejamentoResponse(
    Long id,
    String descricao,
    List<EntradaResponse> entradas
) {

    public PlanejamentoResponse(Planejamento planejamento){
        this(planejamento.getId(), planejamento.getDescricao(), planejamento.getEntradas()
        .stream()
        .map(EntradaResponse::new)
        .toList());
    }

    public record EntradaResponse(
        Long id,
        String dia,
        String refeicao,
        List<PorcaoResponse> porcao
    ){
        public EntradaResponse(EntradaPlanejamento entrada){
            this(entrada.getId(), entrada.getDia(), entrada.getRefeicao(), entrada.getPorcoes()
            .stream()
            .map(PorcaoResponse::new)
            .toList());
        }
    }

    public record PorcaoResponse(
        String alimento,
        String quantidade
    ){
        public PorcaoResponse(Porcao porcao){
            this(porcao.getAlimento(), porcao.getQuantidade());
        }
    }
}