package org.example.controller;

import org.example.DTO.PacienteResponse;
import org.example.model.Nutricionista;
import org.example.model.Paciente;
import org.example.service.NutricionistaService;
import org.example.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.DTO.PacienteResponse;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api/nutricionistas")
public class NutricionistaController {

    private final NutricionistaService nutricionistaService;
    private final PacienteService pacienteService;

    @Autowired
    public NutricionistaController(NutricionistaService nutricionistaService, PacienteService pacienteService) {
        this.nutricionistaService = nutricionistaService;
        this.pacienteService = pacienteService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Nutricionista> cadastrar(@RequestBody Nutricionista nutricionista) {
        Nutricionista salvo = nutricionistaService.cadastrar(nutricionista);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/listaPacientes/{id}")
    public ResponseEntity<List<PacienteResponse>> listaPacientes(@PathVariable Long id) {
        List<PacienteResponse> pacientes = pacienteService.buscarPorNutricionistaId(id);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{nutricionistaId}/associar-paciente/{pacienteId}")
    public ResponseEntity<Void> associarPaciente(
            @PathVariable Long nutricionistaId,
            @PathVariable Long pacienteId
    ) {
        nutricionistaService.associarPaciente(nutricionistaId, pacienteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/painel/{id}") 
    public String mostrarPainelDoNutricionista(@PathVariable Long id, Model model) {

        // 1. Busca a lista de pacientes associados usando o service que já existe
        List<PacienteResponse> listaDePacientes = pacienteService.buscarPorNutricionistaId(id);

        // 2. Adiciona a lista de pacientes ao modelo. 
        model.addAttribute("pacientes", listaDePacientes);

        // 3. Adiciona o ID do nutricionista ao modelo para ser usado no input hidden
        model.addAttribute("nutricionistaId", id);

        // 4. Retorna o nome do arquivo HTML (sem a extensão .html)
        return "listaPacientes"; 
    }
}