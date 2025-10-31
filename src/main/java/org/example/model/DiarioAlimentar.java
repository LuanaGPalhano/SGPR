package org.example.model;

import lombok.*;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;
@Entity
@Table(name = "diario_alimentar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class DiarioAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    @Builder.Default
    private java.time.LocalDateTime registroHorario = java.time.LocalDateTime.now();

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String imgURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @JsonBackReference("paciente-diarios")
    private Paciente paciente;

    @OneToMany(mappedBy = "diario" ,cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference("diario-entradas")
    private List<EntradaDiario> entradasDiario = new java.util.ArrayList<>();

}