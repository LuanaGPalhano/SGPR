package org.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.*;
import org.example.model.Refeicoes;
import org.example.repository.RefeicoesRepository;

@Service
public class RefeicoesService {
    private final RefeicoesRepository repository;

    public RefeicoesService(RefeicoesRepository repository){
        this.repository = repository;
    }

    public Refeicoes criar(Refeicoes refeicao){
        return repository.save(refeicao);
    }

    public List<Refeicoes> listarTodos(){
        return repository.findAll();
    }

    public Optional<Refeicoes> buscaId(Long id){
        return repository.findById(id);
    }

    public List<Refeicoes> listarConjuntos(String conjunto){
        return repository.findByConjuntoRefeicoes(conjunto);
    }

    public Refeicoes atualizar(Long id, Refeicoes refeicaoAtual){
        Optional<Refeicoes> existente = repository.findById(id);

        if(existente.isPresent()){
            Refeicoes refeicao = existente.get();
            refeicao.setConjuntoRefeicoes(refeicaoAtual.getConjuntoRefeicoes());
            refeicao.setAnotacao(refeicaoAtual.getAnotacao());
            return repository.save(refeicao);
        } else {
            throw new RuntimeException("Refeição não pode ser localizada" + id);
        }
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
