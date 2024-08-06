package br.com.rzaninelli.CliniConect.dao;

import br.com.rzaninelli.CliniConect.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

    public Usuario findByLogin(String login);
}
