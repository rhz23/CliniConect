package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeDAO extends JpaRepository<Cidade, Integer> {

    public Cidade findByNomeCidade(String nomeCidade);

    @Query(value = "SELECT c FROM Cidade c inner join Estado e on c.estado = e")
    public List<Cidade> findAll();

    @Query(value = "SELECT c FROM Cidade c inner join Estado e on c.estado = e where c.idCidade =?1")
    public Cidade buscarCidadePorId(Integer idCidade);
}
