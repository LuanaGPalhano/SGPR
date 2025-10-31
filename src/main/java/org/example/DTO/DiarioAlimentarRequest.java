package org.example.DTO;

import org.example.DTO.EntradaDiarioRequest;

import java.util.List;
public record DiarioAlimentarRequest(
    String texto,
    String imgURL,
    String pacienteCpf,
    List<EntradaDiarioRequest> entradas
) { 
}