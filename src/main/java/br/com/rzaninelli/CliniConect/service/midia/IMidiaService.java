package br.com.rzaninelli.CliniConect.service.midia;

import br.com.rzaninelli.CliniConect.model.Midia;
import org.springframework.data.domain.Page;

public interface IMidiaService {

    public Midia cadastraMidia(Midia midia);
    public Midia atualizaMidia(Midia midia);
    public Midia buscarPeloId(Integer id);
    public boolean excluiMidia(Integer id);
}
