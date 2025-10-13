package org.example.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import org.example.model.EntradaDiario;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiarioAlimentarResponse {
    private Long id;
    private String texto;
    private String imgURL;
    private List<EntradaDiario> entradas;
    private LocalDateTime registroHorario;
}

