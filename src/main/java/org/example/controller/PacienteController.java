package org.example.controller;

import org.example.DTO.PacienteCadastroRequest;
import org.example.DTO.PacienteResponse;
import org.example.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastrar") // Rota base representa o recurso "pacientes"
public class PacienteController {

    // O controller agora depende APENAS do service
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * Endpoint para cadastrar um novo paciente.
     * Rota: POST /api/pacientes
     */
    @PostMapping
    public ResponseEntity<PacienteResponse> cadastrar(@RequestBody PacienteCadastroRequest dados) {
        PacienteResponse pacienteSalvo = pacienteService.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }

    /**
     * Endpoint para buscar um paciente pelo CPF.
     * Rota: GET /api/pacientes/cpf/{cpf}
     */
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteResponse> buscarPorCpf(@PathVariable String cpf) {
        // A lógica foi movida para o service. O controller apenas chama o método.
        PacienteResponse paciente = pacienteService.buscarPorCpf(cpf);
        return ResponseEntity.ok(paciente);
    }
}
