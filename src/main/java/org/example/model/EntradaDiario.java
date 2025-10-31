package org.example.model;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "entrada_diario")
@NoArgsConstructor
@AllArgsConstructor

public class EntradaDiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diario_id", nullable = false)
    @JsonBackReference("diario-entradas")
    private DiarioAlimentar diario;
}

