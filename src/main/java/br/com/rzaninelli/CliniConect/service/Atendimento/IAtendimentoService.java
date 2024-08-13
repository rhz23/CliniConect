package br.com.rzaninelli.CliniConect.service.Atendimento;

import br.com.rzaninelli.CliniConect.model.Atendimento;

import java.util.Date;
import java.util.List;

public interface IAtendimentoService {

    Atendimento cadastrarAtendimento(Atendimento Atendimento, Integer idPaciente);
    List<Atendimento> listarAtendimentosPorPaciente(Integer id);
    Atendimento buscarAtendimentoPorId(Integer id);
    Atendimento buscarAtendimentoPorData(Date data);
    Atendimento atualizarAtendimento(Atendimento Atendimento);
    boolean removerAtendimento(Integer id);
}
