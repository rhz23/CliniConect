package br.com.rzaninelli.CliniConect.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_cidade")
public class Cidade {

    @Id
    @Column(name = "id_cidade")
    private Integer idCidade;

}
