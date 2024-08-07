package br.com.rzaninelli.CliniConect.service.usuario;

import br.com.rzaninelli.CliniConect.model.Usuario;
import br.com.rzaninelli.CliniConect.security.CliniToken;

public interface IAuthService {

    public Usuario criarUsuario(Usuario usuario);
    public CliniToken realizarLogin(Usuario dadosLogin);
}
