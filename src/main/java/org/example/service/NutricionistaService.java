package org.example.service;

import org.example.model.Nutricionista;
import org.example.model.Paciente;
import org.example.repository.NutricionistaRepository;
import org.example.repository.PacienteRepository; 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class NutricionistaService {

    private final NutricionistaRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final PacienteRepository pacienteRepository; 

    public NutricionistaService(NutricionistaRepository repository, 
                                PasswordEncoder passwordEncoder, 
                                PacienteRepository pacienteRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.pacienteRepository = pacienteRepository; 
    }

    public Nutricionista cadastrar(Nutricionista nutricionista) {
        if (repository.findByCrnUf(nutricionista.getCrnUf()).isPresent()) {
            throw new IllegalArgumentException("CRN já cadastrado!");
        }
        String senhaCriptografada = passwordEncoder.encode(nutricionista.getSenha());
        nutricionista.setSenha(senhaCriptografada);
        return repository.save(nutricionista);
    }

    public void associarPaciente(Long nutricionistaId, Long pacienteId) {
        // Busca o nutricionista ou lança erro se não encontrar
        Nutricionista nutricionista = repository.findById(nutricionistaId)
                .orElseThrow(() -> new EntityNotFoundException("Nutricionista não encontrado"));

        // Busca o paciente ou lança erro se não encontrar
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        // Cria o vínculo
        paciente.setNutricionista(nutricionista);

        // Salva a alteração no paciente
        pacienteRepository.save(paciente);
    }
}