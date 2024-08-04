package br.com.rzaninelli.CliniConect.service;

import br.com.rzaninelli.CliniConect.dao.PacienteDAO;
import br.com.rzaninelli.CliniConect.model.Paciente;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
        Paciente pacienteTemp = pacienteDAO.findById(paciente.getIdPaciente()).orElse(null);
        if (pacienteTemp != null) {
            var nome = paciente.getNomePaciente();
            if (nome != null)
                pacienteTemp.setNomePaciente(nome);
            var cpf = paciente.getCpfPaciente();
            if (cpf != null)
                pacienteTemp.setCpfPaciente(cpf);
            var sexo = paciente.getSexoPaciente();
            if (sexo != null)
                pacienteTemp.setSexoPaciente(sexo);
            var dataNascimento = paciente.getDataNascimento();
            if (dataNascimento != null)
                pacienteTemp.setDataNascimento(dataNascimento);
            var email = paciente.getEmailPaciente();
            if (email != null)
                pacienteTemp.setEmailPaciente(email);
            var telefone = paciente.getTelefonePaciente();
            if (telefone != null)
                pacienteTemp.setTelefonePaciente(telefone);
            var ativo = paciente.getAtivoPaciente();
            if (ativo != null)
                pacienteTemp.setAtivoPaciente(ativo);
            var linkFoto = paciente.getLinkFoto();
            if (linkFoto != null)
                pacienteTemp.setLinkFoto(linkFoto);
            return pacienteDAO.save(pacienteTemp);
        }
        return pacienteDAO.save(paciente);
    }

    private String nomePaciente;
    private String cpfPaciente;
    private String sexoPaciente;
    private LocalDate dataNascimento;
    private String emailPaciente;
    private String telefonePaciente;
    private Boolean ativoPaciente;
    private String linkFoto;

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
    public Paciente deletarPaciente(Integer id) {
        Paciente paciente = buscarPacientePorId(id);
        if(paciente != null){
            pacienteDAO.deleteById(id);
            return paciente;
        }
        return null;
    }
}
