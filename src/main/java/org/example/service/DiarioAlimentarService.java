package org.example.service;

import java.util.List;
import org.springframework.stereotype.*;
import org.example.model.DiarioAlimentar;
import org.example.model.EntradaDiario;
import org.example.repository.DiarioAlimentarRepository;
import org.example.model.Paciente;
import org.example.repository.PacienteRepository;
import org.example.DTO.DiarioAlimentarRequest;
import org.example.DTO.EntradaDiarioRequest;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DiarioAlimentarService {
    private final DiarioAlimentarRepository diarioRepository;
    private final PacienteRepository pacienteRepository;

    public DiarioAlimentarService(DiarioAlimentarRepository diarioRepository, PacienteRepository pacienteRepository){
        this.diarioRepository = diarioRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public DiarioAlimentar salvar(DiarioAlimentarRequest request) {
        /* DEBUG */
        System.out.println("🧩 CPF recebido: " + request.pacienteCpf());
        System.out.println("🧩 Texto recebido: " + request.texto());
        System.out.println("🧩 Entradas: " + request.entradas());
        /* DEBUG */

        Paciente paciente = pacienteRepository.findByCpf(request.pacienteCpf())
        .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado" + request.pacienteCpf()));

        DiarioAlimentar novoDiario = DiarioAlimentar.builder()
        .texto(request.texto())
        .imgURL(request.imgURL())
        .paciente(paciente)
        .build();

        if(request.entradas() != null && !request.entradas().isEmpty()){
            List<EntradaDiario> entradas = request.entradas().stream()
            .map(req-> {
                EntradaDiario entrada = new EntradaDiario();
                entrada.setDescricao(req.descricao());
                entrada.setDiario(novoDiario);
                return entrada;
            })
            .toList();
            novoDiario.setEntradasDiario(entradas);
        }
        return diarioRepository.save(novoDiario);
    }

    public List<DiarioAlimentar> listarTodos() {
        return diarioRepository.findAll();
    }

    public List<DiarioAlimentar> listarPorPacienteCpf(String cpf){
        Paciente paciente = pacienteRepository.findByCpf(cpf)
        .orElseThrow(()-> new EntityNotFoundException("Paciente com esse cpf não encontrado"));  
        return diarioRepository.findByPacienteId(paciente.getId());
    }

}