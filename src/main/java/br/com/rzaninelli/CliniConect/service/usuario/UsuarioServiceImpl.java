package br.com.rzaninelli.CliniConect.service.usuario;

import br.com.rzaninelli.CliniConect.dao.UsuarioDAO;
import br.com.rzaninelli.CliniConect.model.Usuario;
import br.com.rzaninelli.CliniConect.security.CliniToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    public Usuario criarUsuario(Usuario usuario) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String novaSenha = encoder.encode(usuario.getSenha());
        usuario.setSenha(novaSenha);
        return usuarioDAO.save(usuario);
    }

    @Override
    public CliniToken realizarLogin(Usuario dadosLogin) {
        return null;
    }
}
