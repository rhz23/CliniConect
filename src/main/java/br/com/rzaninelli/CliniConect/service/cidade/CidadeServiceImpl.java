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

    @Override
    public Cidade buscarCidadePorId(int id) {
        return null;
    }

    @Override
    public Cidade buscarCidadePorSigla(String sigla) {
        return null;
    }
}
