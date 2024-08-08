package br.com.rzaninelli.CliniConect.service.estado;

import br.com.rzaninelli.CliniConect.dao.EstadoDAO;
import br.com.rzaninelli.CliniConect.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstadoServiceImpl implements IEstadoService{

    @Autowired
    private EstadoDAO estadoDAO;

    @Override
    public Estado cadastrarEstado(Estado estado) {
        return estadoDAO.save(estado);
    }

    @Override
    public List<Estado> listarEstados() {
        return estadoDAO.findAll();
    }

    @Override
    public Estado buscarEstadoPorId(Integer id) {
        return estadoDAO.findById(id).orElse(null);
    }

    @Override
    public Estado buscarEstadoPorSigla(String sigla) {
        return null;
    }
}
