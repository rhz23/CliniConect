package br.com.rzaninelli.CliniConect.service.endereco;

import br.com.rzaninelli.CliniConect.model.Endereco;

public interface IEnderecoService {

    public Endereco cadastrarEndereco(Endereco endereco, Integer id);
    public Endereco buscarEnderecoPorId(Integer id);
    public Endereco atualizarEndereco(Endereco endereco, Integer id);
    public Endereco deletarEndereco(Integer id);

}
