package br.com.rzaninelli.CliniConect.service;

import br.com.rzaninelli.CliniConect.model.Paciente;

import java.util.List;

public interface IPacienteService {

    public Paciente cadastrarPaciente(Paciente paciente);
    public Paciente atualizarPaciente(Paciente paciente);
    public List<Paciente> listarPacientes();
    public Paciente buscarPacientePorId(int id);
    public List<Paciente> buscarPacientesPorNome(String nome);
    public List<Paciente> buscarPacientesPorCpf(String cpf);
    public List<Paciente> buscarPacientesPorEmail(String email);
    public boolean deletarPaciente(Paciente paciente);
}
