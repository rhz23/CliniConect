package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteDAO extends JpaRepository<Paciente, Integer> {

    public Page<Paciente> findAll(Pageable paginacao);

    public List<Paciente> findByNomePacienteContaining(String palavra);

    public Paciente findPacienteByCpfPaciente(String cpf);

    public Paciente findPacienteByEmailPaciente(String email);
}
