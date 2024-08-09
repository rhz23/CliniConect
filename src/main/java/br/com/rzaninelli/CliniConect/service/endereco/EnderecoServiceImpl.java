package br.com.rzaninelli.CliniConect.service.endereco;

import br.com.rzaninelli.CliniConect.dao.EnderecoDao;
import br.com.rzaninelli.CliniConect.dto.CepResultadoDTO;
import br.com.rzaninelli.CliniConect.model.Cidade;
import br.com.rzaninelli.CliniConect.model.Endereco;
import br.com.rzaninelli.CliniConect.model.Paciente;
import br.com.rzaninelli.CliniConect.service.cidade.CidadeServiceImpl;
import br.com.rzaninelli.CliniConect.service.paciente.PacienteServiceImpl;
import br.com.rzaninelli.CliniConect.utils.consumers.ConsultaCepViaCepAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoServiceImpl implements IEnderecoService {

    @Autowired
    EnderecoDao enderecoDao;

    @Autowired
    CidadeServiceImpl cidadeService;

    @Autowired
    PacienteServiceImpl pacienteService;

    @Autowired
    ConsultaCepViaCepAPI cepViaCepAPI;

    @Override
    public Endereco cadastrarEndereco(Endereco endereco, Integer id) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);

        if (paciente != null) {
            endereco.setPaciente(paciente);
            CepResultadoDTO cepResultado = cepViaCepAPI.recuperarDadosCep(endereco.getCep());
            if (cepResultado != null) {
                if (!cepResultado.getLogradouro().isEmpty())
                    endereco.setLogradouro(cepResultado.getLogradouro());
                if (!cepResultado.getLocalidade().isEmpty()) {
                    Cidade cidade = cidadeService.buscarCidadePorNome(cepResultado.getLocalidade());
                    if (cidade != null) {
                        endereco.setCidade(cidade);
                    }
                }
                endereco = enderecoDao.save(endereco);
                paciente.setEndereco(endereco);
                pacienteService.alterarPaciente(paciente);
                return endereco;
            }
        }
        return null;
    }

    @Override
    public Endereco buscarEnderecoPorId(Integer id) {
        Endereco endereco = enderecoDao.findById(id).orElse(null);
        if (endereco != null) {
            return endereco;
        }
        return null;
    }

    @Override
    public Endereco atualizarEndereco(Endereco endereco, Integer id) {
        Endereco enderecoAtualizado = enderecoDao.findById(id).orElse(null);
        if (enderecoAtualizado != null) {
            var logradouro = endereco.getLogradouro();
            if (!logradouro.isEmpty())
                enderecoAtualizado.setLogradouro(logradouro);
            var complemento = endereco.getComplemento();
            if (!complemento.isEmpty())
                enderecoAtualizado.setComplemento(complemento);
            var cep = endereco.getCep();
            if (!cep.isEmpty())
                enderecoAtualizado.setCep(cep);
            var cidade = endereco.getCidade();
            if(cidade != null)
                enderecoAtualizado.setCidade(cidade);

            return enderecoDao.save(enderecoAtualizado);
        }
        return null;
    }

    @Override
    public Endereco deletarEndereco(Integer id) {
        Endereco endereco = enderecoDao.findById(id).orElse(null);
        if (endereco != null) {
            enderecoDao.deleteById(id);
            return endereco;
        }
        return null;
    }
}
