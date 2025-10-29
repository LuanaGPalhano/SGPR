package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "item_refeicao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRefeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alimento;
    private double quantidade;
    private String unidadeMedida;
    
    @Column(columnDefinition = "JSON") 
    private String resumoNutricional;

    @ManyToOne
    @JoinColumn(name = "refeicao_id")
    private Refeicao refeicao;

    @ManyToOne
    @JoinColumn(name = "entrada_id")
    private EntradaPlanejamento entrada;
}
