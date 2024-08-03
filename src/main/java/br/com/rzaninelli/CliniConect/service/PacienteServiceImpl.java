package br.com.rzaninelli.CliniConect.service;

import br.com.rzaninelli.CliniConect.dao.PacienteDAO;
import br.com.rzaninelli.CliniConect.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PacienteServiceImpl implements IPacienteService{

    @Autowired
    private PacienteDAO pacienteDAO;

    @Override
    public Paciente cadastrarPaciente(Paciente paciente) {
        paciente.setAtivoPaciente(true);
        return pacienteDAO.save(paciente);
    }

    @Override
    public Paciente alterarPaciente(Paciente paciente) {
        return pacienteDAO.save(paciente);
    }

    @Override
    public List<Paciente> listarPacientes() {
        return pacienteDAO.findAll();
    }

    @Override
    public Paciente buscarPacientePorId(int id) {
        return pacienteDAO.findById(id).orElse(null);
    }

    @Override
    public List<Paciente> buscarPacientesPorNome(String nome) {
        return pacienteDAO.findByNomePacienteContaining(nome);
    }

    @Override
    public Paciente buscarPacientesPorCpf(String cpf) {
        //TODO: verificar retorno se nenhum encontrado e corrigir se necessário
        return pacienteDAO.findPacienteByCpfPaciente(cpf);
    }

    @Override
    public Paciente buscarPacientesPorEmail(String email) {
        return pacienteDAO.findPacienteByEmailPaciente(email);
    }

    @Override
    public boolean deletarPaciente(Integer id) {
        Paciente paciente = buscarPacientePorId(id);
        if(paciente != null){
            pacienteDAO.delete(paciente);
            return true;
        }
        return false;
    }
}
