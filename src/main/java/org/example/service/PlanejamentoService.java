package org.example.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.DTO.ResumoNutricionalResponse;
import org.example.model.EntradaPlanejamento;
import org.example.model.ItemRefeicao;
import org.example.model.Planejamento;
import org.example.repository.PlanejamentoRepository;
import org.springframework.stereotype.Service;
import org.example.service.ResumoNutricionalService;

@Service
public class PlanejamentoService {
    private final PlanejamentoRepository repository;
    private final ResumoNutricionalService resumoService;

    public PlanejamentoService(PlanejamentoRepository repository, ResumoNutricionalService resumoService){
        this.repository = repository;
        this.resumoService = resumoService;
    }

     public Planejamento salvar(Planejamento planejamento) {
        return repository.save(planejamento);
    }

    public List<Planejamento> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id){
       repository.deleteById(id);
    }

    public ResumoNutricionalResponse gerarResumoNutricional(Long planejamentoId) {
    Planejamento planejamento = repository.findById(planejamentoId)
        .orElseThrow(() -> new RuntimeException("Planejamento não encontrado"));

    List<Map<String, String>> porcoes = planejamento.getEntradas().stream()
        .flatMap((entrada) -> entrada.getItensRefeicao().stream())
        .map((item) -> Map.of(
            "alimento", item.getAlimento(),
            "quantidade", String.valueOf(item.getQuantidade())
        ))
        .collect(Collectors.toList());

    return resumoService.gerarResumo(porcoes);
    }
}