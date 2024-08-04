package br.com.rzaninelli.CliniConect.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atendimento")
    private Integer idAtendimento;

    @Column(name = "data_atendimento")
    private LocalDateTime dataAtendimento;

    @Column(name = "info_atendimento")
    private String infoAtendimento;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    @JsonBackReference("atendimentos")
    private Paciente paciente;

    public Integer getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Integer idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getInfoAtendimento() {
        return infoAtendimento;
    }

    public void setInfoAtendimento(String infoAtendimento) {
        this.infoAtendimento = infoAtendimento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}