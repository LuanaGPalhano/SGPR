package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Entity
@Table(name = "refeicoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalTime horario;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "dieta_id")
    private Dieta dieta;

    @OneToMany(mappedBy = "refeicao", cascade = CascadeType.ALL)
    private java.util.List<ItemRefeicao> itens;

    public Refeicao(String nome, LocalTime horario, String descricao, Dieta dieta) {
        this.nome = nome;
        this.horario = horario;
        this.descricao = descricao;
        this.dieta = dieta;
    }

}
