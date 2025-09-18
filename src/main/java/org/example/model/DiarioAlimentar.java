package org.example.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diario")

public class DiarioAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne  
    @JoinColumn(name="paciente_id")
    private Paciente paciente;
    private String texto;
    private String imgURL;
    @OneToMany(mappedBy = "anotacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Refeicoes> refeicoes;
    private LocalDateTime registroHorario = LocalDateTime.now();
}

