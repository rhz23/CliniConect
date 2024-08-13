package br.com.rzaninelli.CliniConect.service.paciente;

import br.com.rzaninelli.CliniConect.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPacienteService {

    Paciente cadastrarPaciente(Paciente paciente);
    Paciente alterarPaciente(Paciente paciente);
    Page<Paciente> listarPacientes(Pageable paginacao);
    Paciente buscarPacientePorId(int id);
    Page<Paciente> buscarPacientesPorNome(String nome, Pageable pagina);
    Paciente buscarPacientesPorCpf(String cpf);
    Paciente buscarPacientesPorEmail(String email);
    Paciente deletarPaciente(Integer id);
}
