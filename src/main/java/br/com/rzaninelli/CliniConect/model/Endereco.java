package br.com.rzaninelli.CliniConect.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_enderecos")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Integer idEndereco;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cep")
    private String cep;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    @JsonBackReference("cidades")
    private Cidade cidade;

}
