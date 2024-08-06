package br.com.rzaninelli.CliniConect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_estados")
public class Estado {

    @Id
    @Column(name = "id_estado")
    private Integer idEstado;

    @Column(name = "nome_estado")
    private String nomeEstado;

    @Column(name = "sigla_estado")
    private String siglaEstado;

    @OneToMany(mappedBy = "estado", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("estado")
    private List<Cidade> cidades;

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
