package br.com.rzaninelli.CliniConect.service.cidade;

import br.com.rzaninelli.CliniConect.model.Cidade;

import java.util.List;

public interface ICidadeService {

    public Cidade cadastrarCidade(Cidade Cidade);
    public List<Cidade> listarCidades();
    public Cidade buscarCidadePorId(Integer id);
    public Cidade buscarCidadePorNome(String nome);
}
