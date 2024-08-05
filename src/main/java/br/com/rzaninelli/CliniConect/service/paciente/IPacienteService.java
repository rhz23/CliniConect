package br.com.rzaninelli.CliniConect.service.paciente;

import br.com.rzaninelli.CliniConect.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPacienteService {

    public Paciente cadastrarPaciente(Paciente paciente);
    public Paciente alterarPaciente(Paciente paciente);
    public Page<Paciente> listarPacientes(Pageable paginacao);
    public Paciente buscarPacientePorId(int id);
    public List<Paciente> buscarPacientesPorNome(String nome, Pageable pagina);
    public Paciente buscarPacientesPorCpf(String cpf);
    public Paciente buscarPacientesPorEmail(String email);
    public Paciente deletarPaciente(Integer id);
}
