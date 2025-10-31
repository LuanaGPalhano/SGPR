package org.example.controller;

import org.example.model.DiarioAlimentar;
import org.example.service.DiarioAlimentarService;
import org.example.DTO.DiarioAlimentarRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diario")
@CrossOrigin(origins = "*")
public class DiarioAlimentarController {

    private final DiarioAlimentarService service;

    public DiarioAlimentarController(DiarioAlimentarService service) {
        this.service = service;
    }

    @PostMapping
    public DiarioAlimentar salvar(@RequestBody DiarioAlimentarRequest diarioRequest) {
        return service.salvar(diarioRequest);
    }

    @GetMapping
    public List<DiarioAlimentar> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("paciente/{cpf}")
    public List<DiarioAlimentar> listarPorPacienteCpf(@PathVariable String cpf){
        return service.listarPorPacienteCpf(cpf);
    }
}