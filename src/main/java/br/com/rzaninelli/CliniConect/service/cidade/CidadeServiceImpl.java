package br.com.rzaninelli.CliniConect.service.cidade;

import br.com.rzaninelli.CliniConect.dao.CidadeDAO;
import br.com.rzaninelli.CliniConect.model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CidadeServiceImpl implements ICidadeService{

    @Autowired
    private CidadeDAO cidadeDAO;

    @Override
    public Cidade cadastrarCidade(Cidade Cidade) {
        return cidadeDAO.save(Cidade);
    }

    @Override
    public List<Cidade> listarCidades() {
        return cidadeDAO.findAll();
    }

//    @Override
//    public Cidade buscarCidadePorId(Integer id) {
//        return cidadeDAO.findById(id).orElse(null);
//    }

    @Override
    public Cidade buscarCidadePorId(Integer id) {
        return cidadeDAO.buscarCidadePorId(id);

    }

    @Override
    public Cidade buscarCidadePorNome(String nome) {
        return cidadeDAO.findByNomeCidade(nome);
    }
}
