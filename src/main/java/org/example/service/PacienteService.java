package org.example.service;

import jakarta.persistence.EntityNotFoundException; 
import org.example.DTO.PacienteCadastroRequest;
import org.example.DTO.PacienteResponse;
import org.example.model.Paciente;
import org.example.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {
    private final PacienteRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PacienteService(PacienteRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public PacienteResponse cadastrar(PacienteCadastroRequest dados) {
        // 1. Limpa o CPF para consistência no banco de dados
        String cpfLimpo = dados.cpf().replaceAll("[^0-9]", "");

        // 2. Verifica se o CPF limpo já existe
        if (repository.findByCpf(cpfLimpo).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }

        Paciente novoPaciente = new Paciente();
        novoPaciente.setNome(dados.nome());
        novoPaciente.setEmail(dados.email());
        novoPaciente.setSenha(passwordEncoder.encode(dados.senha()));
        
        // 3. Salva o CPF limpo no banco
        novoPaciente.setCpf(cpfLimpo);

        Paciente pacienteSalvo = repository.save(novoPaciente);
        return new PacienteResponse(pacienteSalvo);
    }

    public List<Paciente> buscarPorNutricionistaId(Long id) {
        return repository.findByNutricionista_Id(id);
    }
    
    public PacienteResponse buscarPorCpf(String cpf) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        
        return repository.findByCpf(cpfLimpo)
                .map(PacienteResponse::new)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o CPF: " + cpf));
    }
}
