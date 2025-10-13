package org.example.controller;

import org.example.DTO.SemanaRequest;
import org.example.DTO.SemanaResponse;
import org.example.model.Planejamento;
import org.example.service.PlanejamentoService;
import org.example.service.SemanaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/planejamento")
@CrossOrigin(origins = {"http://localhost:5501", "http://127.0.0.1:5501"})
public class PlanejamentoController {
    
    private final PlanejamentoService service;
    private final SemanaService semanaService;

    public PlanejamentoController(PlanejamentoService service, SemanaService semanaService) {
        this.service = service;
        this.semanaService = semanaService;
    }

    @PostMapping
    public Planejamento salvar(@RequestBody Planejamento planejamento) {
        return service.salvar(planejamento);
    }

    @PostMapping("/semanas")
    public ResponseEntity<List<SemanaResponse>> listarSemanas(@RequestBody SemanaRequest request) {
        try {
            LocalDate inicio = LocalDate.parse(request.getInicio(), DateTimeFormatter.ISO_LOCAL_DATE);
            List<SemanaResponse> semanas = semanaService.listarSemanas(inicio);
            return ResponseEntity.ok(semanas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Planejamento> listarTodos() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
}
