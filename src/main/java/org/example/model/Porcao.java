package org.example.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "planejamento_porcao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Porcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alimento;
    private String quantidade;

    @ManyToOne
    @JoinColumn(name = "entrada_id")
    private EntradaPlanejamento entrada;
}
