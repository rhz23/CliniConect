package br.com.rzaninelli.CliniConect.service.Atendimento;

import br.com.rzaninelli.CliniConect.dao.AtendimentoDAO;
import br.com.rzaninelli.CliniConect.model.Atendimento;
import br.com.rzaninelli.CliniConect.model.Paciente;
import br.com.rzaninelli.CliniConect.service.paciente.IPacienteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class AtendimentoServiceImpl implements IAtendimentoService{

    @Autowired
    AtendimentoDAO atendimentoDAO;

    @Autowired
    IPacienteService pacienteService;

    @Transactional
    @Override
    public Atendimento cadastrarAtendimento(Atendimento atendimento, Integer idPaciente) {

        Paciente paciente = pacienteService.buscarPacientePorId(idPaciente);

        if (paciente != null) {
            atendimento.setPaciente(paciente);
            if (atendimento.getDataAtendimento() == null)
                atendimento.setDataAtendimento(LocalDateTime.now());
            atendimento = atendimentoDAO.save(atendimento);
            return atendimento;
        }

        return null;
    }



    @Override
    public List<Atendimento> listarAtendimentosPorPaciente(Integer id) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        if (paciente != null) {
            return atendimentoDAO.findByPaciente(paciente);
        }
        return null;
    }

    @Override
    public Atendimento buscarAtendimentoPorId(Integer id) {
        return null;
    }

    @Override
    public Atendimento buscarAtendimentoPorData(Date data) {
        return null;
    }

    @Override
    public Atendimento atualizarAtendimento(Atendimento atendimento) {
        return atendimentoDAO.save(atendimento);
    }

    @Override
    public boolean removerAtendimento(Integer id) {
        if (atendimentoDAO.existsById(id)) {
             atendimentoDAO.deleteById(id);
             return true;
        }
        return false;
    }
    //TODO 04/08/2024 rhzan:
}
