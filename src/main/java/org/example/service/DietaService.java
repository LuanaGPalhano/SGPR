package org.example.service;

import org.example.model.Dieta;
import org.example.repository.DietaRepository;
import org.springframework.stereotype.Service;

@Service
public class DietaService {
    private final DietaRepository dietaRepository;

    public DietaService(DietaRepository dietaRepository) {
        this.dietaRepository = dietaRepository;
    }

    public Dieta criarDieta(Dieta dieta) {
        return dietaRepository.save(dieta);
    }

    public Dieta buscarDietaPorPacienteId(Long pacienteId) {
        return dietaRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new RuntimeException("Dieta não encontrada para o paciente ID: " + pacienteId));
    }

    public void deletarDieta(Long dietaId) {
        dietaRepository.deleteById(dietaId);
    }

    public Dieta atualizarDieta(Long dietaId, Dieta dietaAtualizada) {
        Dieta dietaExistente = dietaRepository.findById(dietaId)
                .orElseThrow(() -> new RuntimeException("Dieta não encontrada com ID: " + dietaId));

        if (dietaAtualizada.getRefeicoes() != null) {
            dietaExistente.setRefeicoes(dietaAtualizada.getRefeicoes());
            
            dietaExistente.getRefeicoes().forEach(refeicao -> refeicao.setDieta(dietaExistente)); 
        }
    

        return dietaRepository.save(dietaExistente);
    }

}
