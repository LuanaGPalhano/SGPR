package org.example.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "refeicoes")
@NoArgsConstructor
@AllArgsConstructor

public class Refeicoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String conjuntoRefeicoes;

    @ManyToOne
    @JoinColumn(name = "diario_id")
    private DiarioAlimentar diario;
}

