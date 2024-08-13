package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteDAO extends JpaRepository<Paciente, Integer> {

    Page<Paciente> findAll(Pageable paginacao);

    Page<Paciente> findByNomePacienteContaining(String palavra, Pageable paginaca);

    Paciente findPacienteByCpfPaciente(String cpf);

    Paciente findPacienteByEmailPaciente(String email);
}
