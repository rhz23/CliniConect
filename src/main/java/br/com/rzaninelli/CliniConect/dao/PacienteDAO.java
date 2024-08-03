package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteDAO extends JpaRepository<Paciente, Integer> {

    public List<Paciente> findByNomePacienteContaining(String palavra);

    public Paciente findPacienteByCpfPaciente(String cpf);

    public Paciente findPacienteByEmailPaciente(String email);
}
