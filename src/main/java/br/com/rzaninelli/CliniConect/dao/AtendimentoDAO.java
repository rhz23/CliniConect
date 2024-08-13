package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Atendimento;
import br.com.rzaninelli.CliniConect.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoDAO extends JpaRepository<Atendimento, Integer> {

    List<Atendimento> findByPaciente (Paciente paciente);
}
