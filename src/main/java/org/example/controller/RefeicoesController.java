package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.model.Refeicoes;
import org.example.service.RefeicoesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/refeicoes")
public class RefeicoesController {
    private final RefeicoesService service;

    public RefeicoesController(RefeicoesService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Refeicoes> criar(@RequestBody Refeicoes refeicao){
        return ResponseEntity.ok(service.criar(refeicao));
    }

    @GetMapping
    public ResponseEntity<List<Refeicoes>> listarTodos(){
            return ResponseEntity.ok(service.listarTodos());
        }
    
    @GetMapping("/{id}")
        ResponseEntity<Refeicoes> buscaId(@PathVariable Long id){
            Optional<Refeicoes> refeicao = service.buscaId(id);
            if(refeicao.isPresent()){
                return ResponseEntity.ok(refeicao.get());
            }
                else{
                    return ResponseEntity.notFound().build();
                }
         }
}
