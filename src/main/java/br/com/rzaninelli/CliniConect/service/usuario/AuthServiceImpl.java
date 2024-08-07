package br.com.rzaninelli.CliniConect.service.usuario;

import br.com.rzaninelli.CliniConect.dao.UsuarioDAO;
import br.com.rzaninelli.CliniConect.model.Usuario;
import br.com.rzaninelli.CliniConect.security.CliniToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements IAuthService {

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

        Usuario resultado = usuarioDAO.findByLogin(dadosLogin.getLogin());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (resultado != null) {
            if(encoder.matches(dadosLogin.getSenha(), resultado.getSenha())) {
                return new CliniToken("Token123");
            }
        }
        return null;
    }
}
