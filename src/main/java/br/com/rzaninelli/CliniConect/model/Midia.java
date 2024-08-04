package br.com.rzaninelli.CliniConect.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_midias")
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_seq")
    private Integer numSequencial;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "link_midia")
    private String linkMidia;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    @JsonBackReference("midias")
    private Paciente paciente;

    public Integer getNumSequencial() {
        return numSequencial;
    }

    public void setNumSequencial(Integer numSequencial) {
        this.numSequencial = numSequencial;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLinkMidia() {
        return linkMidia;
    }

    public void setLinkMidia(String linkMidia) {
        this.linkMidia = linkMidia;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
