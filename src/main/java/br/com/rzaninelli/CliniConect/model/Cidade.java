package br.com.rzaninelli.CliniConect.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cidades")
public class Cidade {

    @Id
    @Column(name = "id_cidade")
    private Integer idCidade;

    @Column(name = "nome_cidade")
    private String nomeCidade;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
