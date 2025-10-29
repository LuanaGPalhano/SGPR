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
        // Validação de dados obrigatórios
        if (dados.nome() == null || dados.nome().isEmpty()) {
            throw new IllegalArgumentException("O nome do paciente é obrigatório.");
        }
        if (dados.cpf() == null || dados.cpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF do paciente é obrigatório.");
        }
        if (dados.email() == null || dados.email().isEmpty()) {
            throw new IllegalArgumentException("O e-mail do paciente é obrigatório.");
        }
        if (dados.senha() == null || dados.senha().isEmpty()) {
            throw new IllegalArgumentException("A senha do paciente é obrigatória.");
        }

        // Limpa o CPF para consistência no banco de dados
        String cpfLimpo = dados.cpf().replaceAll("[^0-9]", "");

        // Verifica se o CPF limpo já existe
        if (repository.findByCpf(cpfLimpo).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }

        // Criação do paciente
        Paciente novoPaciente = new Paciente(
            dados.nome(),
            cpfLimpo, 
            dados.email(), 
            passwordEncoder.encode(dados.senha())
        );

        // Persistência no banco de dados
        Paciente pacienteSalvo = repository.save(novoPaciente);
        return new PacienteResponse(pacienteSalvo);
    }

    public List<Paciente> buscarPorNutricionistaId(Long id) {
        return repository.findByNutricionista_Id(id);
    }
    
    public PacienteResponse buscarPorCpf(String cpf) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        
        // Ajuste para buscar CPF considerando diferentes formatos
        return repository.findAll().stream()
                .filter(paciente -> paciente.getCpf().replaceAll("[^0-9]", "").equals(cpfLimpo))
                .findFirst()
                .map(PacienteResponse::new)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o CPF: " + cpf));
    }
}
