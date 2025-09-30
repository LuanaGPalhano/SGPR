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
    private Long id;
    private String alimento;
    private double quantidade;
    private String unidadeMedida;
    private double calorias;
    @ManyToOne
    @JoinColumn(name = "refeicao_id")
    private Refeicao refeicao;

}
