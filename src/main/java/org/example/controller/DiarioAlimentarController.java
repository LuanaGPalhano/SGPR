package org.example.controller;

import org.example.model.DiarioAlimentar;
import org.example.service.DiarioAlimentarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/diario")
class DiarioAlimentarController {
    private final DiarioAlimentarService service;

    public DiarioAlimentarController(DiarioAlimentarService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DiarioAlimentar> criarAnotacao(@RequestBody DiarioAlimentar diario){
        return ResponseEntity.ok(service.criarAnotacao(diario));
    }
    
    @GetMapping
        List<DiarioAlimentar> all(){
            return service.listarTodos();
        }

    @GetMapping("/{id}")
        ResponseEntity<DiarioAlimentar> buscaId(@PathVariable Long id){
            Optional<DiarioAlimentar> diario = service.buscaId(id);
            if(diario.isPresent()){
                return ResponseEntity.ok(diario.get());
            }
                else{
                    return ResponseEntity.notFound().build();
                }
         }

    @GetMapping("/paciente/{pacienteId}")
        List<DiarioAlimentar> listarId(@PathVariable Long pacienteId){
            return service.listarId(pacienteId);
        }

    @DeleteMapping("/{id}")
        ResponseEntity<Void> deletar(@PathVariable Long id){
            if(service.deletar(id)){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        }
    }