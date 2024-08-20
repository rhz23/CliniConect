package br.com.rzaninelli.CliniConect.service.paciente;

import br.com.rzaninelli.CliniConect.dao.*;
import br.com.rzaninelli.CliniConect.dto.CepResultadoDTO;
import br.com.rzaninelli.CliniConect.model.*;
import br.com.rzaninelli.CliniConect.utils.consumers.ConsultaCepViaCepAPI;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PacienteServiceImpl implements IPacienteService{

    private static final Logger log = LoggerFactory.getLogger(PacienteServiceImpl.class);

    @Autowired
    private PacienteDAO pacienteDAO;

    @Autowired
    private CidadeDAO cidadeDAO;

    @Autowired
    private EnderecoDao enderecoDao;

    @Autowired
    private MidiaDAO midiaDAO;

    @Autowired
    private AtendimentoDAO atendimentoDAO;

    @Autowired
    ConsultaCepViaCepAPI cepViaCepAPI;


    @Transactional
    @Override
    public Paciente cadastrarPaciente(Paciente paciente) {

        for (Midia midia : paciente.getMidias()) {
            midia.setPaciente(paciente);
            paciente.addMidia(midia);
        }

        for (Atendimento atendimento : paciente.getAtendimentos()) {
            atendimento.setPaciente(paciente);
            paciente.addAtendimento(atendimento);
        }

        if (paciente.getEndereco() != null) {
            if (paciente.getEndereco().getCep() != null) {
                CepResultadoDTO cepResultadoDTO = cepViaCepAPI.recuperarDadosCep(paciente.getEndereco().getCep());
                paciente.getEndereco().setLogradouro(cepResultadoDTO.getLogradouro());
                paciente.getEndereco().setCidade(cidadeDAO.findByNomeCidade(cepResultadoDTO.getLocalidade()));
            }
            Paciente pacienteSalvo = pacienteDAO.save(paciente);
            Endereco endereco = pacienteSalvo.getEndereco();
            endereco.setPaciente(pacienteSalvo);
            pacienteSalvo.setEndereco(endereco);
            return pacienteSalvo;
        }
        return pacienteDAO.save(paciente);
    }

//TODO 19/08/2024 rhzan: refatorar conforme anotações feitas no NOTION (criar testes para essa classe)

    @Transactional
    @Override
    public Paciente alterarPaciente(Paciente paciente) {
        Paciente pacienteTemp = pacienteDAO.findById(paciente.getIdPaciente()).orElse(null);

        if (pacienteTemp != null) {
            // Atualizar informações básicas
            pacienteTemp.setNomePaciente(paciente.getNomePaciente());
            pacienteTemp.setCpfPaciente(paciente.getCpfPaciente());
            pacienteTemp.setSexoPaciente(paciente.getSexoPaciente());
            pacienteTemp.setDataNascimento(paciente.getDataNascimento());
            pacienteTemp.setEmailPaciente(paciente.getEmailPaciente());
            pacienteTemp.setTelefonePaciente(paciente.getTelefonePaciente());
            pacienteTemp.setAtivoPaciente(paciente.getAtivoPaciente());
            pacienteTemp.setLinkFoto(paciente.getLinkFoto());

            // Atualizar mídias
            pacienteTemp.getMidias().clear(); // Limpa as mídias existentes
            for (Midia midia : paciente.getMidias()) {
                if (midia.getNumSequencial()!= null) {
                    // Confere se Midia é entidade existe
                    midia = midiaDAO.findById(midia.getNumSequencial()).orElse(midia);
                    midia.setPaciente(pacienteTemp);
                } else {
                    // Se a midia não tem um ID, é uma nova entidade
                    midia.setPaciente(pacienteTemp);
                }
                pacienteTemp.getMidias().add(midia); // Adiciona as novas mídias
            }

            //Atualizar Atendimentos
            pacienteTemp.getAtendimentos().clear();
            for (Atendimento atendimento : paciente.getAtendimentos()) {
                if (atendimento.getIdAtendimento() != null) {
                    Atendimento atendimentoExistente = atendimentoDAO.findById(atendimento.getIdAtendimento()).orElse(null);
                    if (atendimentoExistente != null) {
                        atendimentoExistente.setInfoAtendimento(atendimento.getInfoAtendimento());
                        atendimentoExistente.setDataAtendimento(atendimento.getDataAtendimento());
                        pacienteTemp.getAtendimentos().add(atendimentoExistente);
                    }
                } else {
                    atendimento.setPaciente(pacienteTemp);
                    pacienteTemp.getAtendimentos().add(atendimento);
                }
            }


            // Atualizar ou criar endereço
            if (paciente.getEndereco() != null) {
                Endereco enderecoTemp = pacienteTemp.getEndereco();
                if (enderecoTemp == null) {
                    enderecoTemp = new Endereco();
                    pacienteTemp.setEndereco(enderecoTemp);
                }
                enderecoTemp.setCep(paciente.getEndereco().getCep());
                enderecoTemp.setLogradouro(paciente.getEndereco().getLogradouro());
                enderecoTemp.setComplemento(paciente.getEndereco().getComplemento());

                // Atualizar cidade
                if (paciente.getEndereco().getCidade() != null) {
                    Cidade cidade = cidadeDAO.findByNomeCidade(paciente.getEndereco().getCidade().getNomeCidade());
                    if (cidade != null) {
                        enderecoTemp.setCidade(cidade);
                    }
                }
            } else {
                pacienteTemp.setEndereco(null);
            }

            //TODO 19/08/2024 rhzan:  verificar cenários após a  alteração se há quebra quando ha alteração de endereço, midia ou atendimentos
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
