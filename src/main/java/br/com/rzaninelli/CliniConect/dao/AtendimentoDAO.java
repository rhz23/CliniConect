package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoDAO extends JpaRepository<Atendimento, Integer> {
}
