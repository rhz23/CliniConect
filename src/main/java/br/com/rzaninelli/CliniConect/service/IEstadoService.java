package br.com.rzaninelli.CliniConect.service;

import br.com.rzaninelli.CliniConect.model.Estado;

import java.util.List;

public interface IEstadoService {

    public Estado cadastrarEstado(Estado estado);
    public List<Estado> listarEstados();
    public Estado buscarEstadoPorId(int id);
    public Estado buscarEstadoPorSigla(String sigla);
}
