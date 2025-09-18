package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estadoCivil;
    private String ocupacao;
    private String alergias;
    private String medicamentos;
    private String suplementacao;
    private String historicoFamiliar;
    private String outrasCondicoes;
    private boolean isBebe;
    private boolean isFuma;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @OneToOne
    @JoinColumn(name = "avaliacao_id")
    private Avaliacao avaliacao;

    public Historico(String estadoCivil, String ocupacao, String alergias, String medicamentos, String suplementacao, String historicoFamiliar, String outrasCondicoes, boolean isBebe, boolean isFuma) {
        this.estadoCivil = estadoCivil;
        this.ocupacao = ocupacao;
        this.alergias = alergias;
        this.medicamentos = medicamentos;
        this.suplementacao = suplementacao;
        this.historicoFamiliar = historicoFamiliar;
        this.outrasCondicoes = outrasCondicoes;
        this.isBebe = isBebe;
        this.isFuma = isFuma;
    }
    
    public boolean isBebe() {
        return isBebe;
    }   

    public boolean isFuma() {
        return isFuma;
    }

    public void setBebe(boolean bebe) {
        isBebe = bebe;
    }

    public void setFuma(boolean fuma) {
        isFuma = fuma;
    }

    public Long getId() {
        return id;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public String getAlergias() {
        return alergias;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public String getSuplementacao() {
        return suplementacao;
    }

    public String getHistoricoFamiliar() {
        return historicoFamiliar;
    }

    public String getOutrasCondicoes() {
        return outrasCondicoes;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void setSuplementacao(String suplementacao) {
        this.suplementacao = suplementacao;
    }

    public void setHistoricoFamiliar(String historicoFamiliar) {
        this.historicoFamiliar = historicoFamiliar;
    }

    public void setOutrasCondicoes(String outrasCondicoes) {
        this.outrasCondicoes = outrasCondicoes;
    }



}
