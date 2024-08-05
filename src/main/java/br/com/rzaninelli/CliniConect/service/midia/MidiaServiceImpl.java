package br.com.rzaninelli.CliniConect.service.midia;

import br.com.rzaninelli.CliniConect.dao.MidiaDAO;
import br.com.rzaninelli.CliniConect.model.Midia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MidiaServiceImpl implements IMidiaService {

    @Autowired
    private MidiaDAO midiaDAO;

    @Override
    public Midia cadastraMidia(Midia midia) {
        return midiaDAO.save(midia);
    }

    @Override
    public Midia atualizaMidia(Midia midia) {
        return midiaDAO.save(midia);
    }

    @Override
    public Midia buscarPeloId(Integer id) {
        return midiaDAO.findById(id).orElse(null);
    }

    @Override
    public boolean excluiMidia(Integer id) {
        if (midiaDAO.existsById(id)) {
            midiaDAO.deleteById(id);
            return true;
        }
        return false;
    }
}
