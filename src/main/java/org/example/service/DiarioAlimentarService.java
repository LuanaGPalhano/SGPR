package org.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.*;
import org.example.model.DiarioAlimentar;
import org.example.repository.DiarioAlimentarRepository;

@Service
public class DiarioAlimentarService {
    private final DiarioAlimentarRepository repository;

    public DiarioAlimentarService(DiarioAlimentarRepository repository){
        this.repository = repository;
    }

    public DiarioAlimentar criarAnotacao(DiarioAlimentar diario){
        return repository.save(diario);
    }

    public List<DiarioAlimentar> listarTodos(){
        return repository.findAll();
    }

    public Optional<DiarioAlimentar> buscaId(Long id){
        return repository.findById(id);
    }
    
    public List<DiarioAlimentar> listarId(Long pacienteId){
        return repository.findByPacienteId(pacienteId);
    }

    public boolean deletar(Long id){
            if(repository.existsById(id)){
                repository.deleteById(id);
                return true;
            }
            return false;
        }
}
