package br.com.rzaninelli.CliniConect.service.paciente;

import br.com.rzaninelli.CliniConect.dao.PacienteDAO;
import br.com.rzaninelli.CliniConect.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

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

    @Override
    public Page<Paciente> listarPacientes(Pageable paginacao) {
        return pacienteDAO.findAll(paginacao);
    }

    @Override
    public Paciente buscarPacientePorId(int id) {
        return pacienteDAO.findById(id).orElse(null);
    }

    @Override
    public Page<Paciente> buscarPacientesPorNome(String nome, Pageable pagina) {
        //TODO: verificar retorno se nenhum encontrado e corrigir se necessário
        return pacienteDAO.findByNomePacienteContaining(nome, pagina);
    }

    @Override
    public Paciente buscarPacientesPorCpf(String cpf) {
        //TODO: verificar retorno se nenhum encontrado e corrigir se necessário
        return pacienteDAO.findPacienteByCpfPaciente(cpf);
    }

    @Override
    public Paciente buscarPacientesPorEmail(String email) {
        //TODO: verificar retorno se nenhum encontrado e corrigir se necessário
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
