package br.com.rzaninelli.CliniConect.service.endereco;

import br.com.rzaninelli.CliniConect.dao.EnderecoDao;
import br.com.rzaninelli.CliniConect.dto.CepResultadoDTO;
import br.com.rzaninelli.CliniConect.model.Cidade;
import br.com.rzaninelli.CliniConect.model.Endereco;
import br.com.rzaninelli.CliniConect.service.cidade.CidadeServiceImpl;
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
    ConsultaCepViaCepAPI cepViaCepAPI;

    @Override
    public Endereco cadastrarEndereco(Endereco endereco) {

        CepResultadoDTO cepResultado = cepViaCepAPI.recuperarDadosCep(endereco.getCep());
        Endereco novoEndereco = new Endereco();
        if (cepResultado != null) {
            if (!cepResultado.getLogradouro().isEmpty())
                novoEndereco.setLogradouro(cepResultado.getLogradouro());
            if (!cepResultado.getLocalidade().isEmpty()){
                Cidade cidade = cidadeService.buscarCidadePorNome(cepResultado.getLocalidade());
                if (cidade != null)
                    novoEndereco.setCidade(cidade);
            }
        }
        return enderecoDao.save(novoEndereco);
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
    public Endereco atualizarEndereco(Endereco endereco) {
        Endereco enderecoAtualizado = enderecoDao.findById(endereco.getIdEndereco()).orElse(null);
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
        return enderecoDao.save(endereco);
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
