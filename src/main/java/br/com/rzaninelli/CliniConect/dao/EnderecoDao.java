package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoDao extends JpaRepository<Endereco, Integer> {
}
