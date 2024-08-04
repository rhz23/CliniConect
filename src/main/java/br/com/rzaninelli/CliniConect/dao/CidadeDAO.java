package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeDAO extends JpaRepository<Cidade, Integer> {
}
