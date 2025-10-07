package org.example.controller;

import org.example.model.Nutricionista;
import org.example.model.Paciente;
import org.example.service.NutricionistaService;
import org.example.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Paciente>> listaPacientes(@PathVariable Long id) {
        List<Paciente> pacientes = pacienteService.buscarPorNutricionistaId(id);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{nutricionistaId}/associar-paciente/{pacienteId}")
    public ResponseEntity<Void> associarPaciente(
            @PathVariable Long nutricionistaId,
            @PathVariable Long pacienteId
    ) {
        // Vamos criar este método no service
        nutricionistaService.associarPaciente(nutricionistaId, pacienteId);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content (sucesso sem corpo)
    }
}