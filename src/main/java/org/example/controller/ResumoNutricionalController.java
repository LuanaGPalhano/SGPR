package org.example.controller;

import org.example.DTO.ResumoNutricionalRequest;
import org.example.DTO.ResumoNutricionalResponse;
import org.example.service.ResumoNutricionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resumo-nutricional")
public class ResumoNutricionalController {
    private final ResumoNutricionalService service;

    public ResumoNutricionalController(ResumoNutricionalService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResumoNutricionalResponse> gerarResumo(@RequestBody ResumoNutricionalRequest request) {
        try{
            ResumoNutricionalResponse resumo = service.gerarResumo(request.porcoes());
            return ResponseEntity.ok(resumo);
        } catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
}
