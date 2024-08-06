package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.model.Usuario;
import br.com.rzaninelli.CliniConect.service.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario resultado = usuarioService.criarUsuario(usuario);
        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.badRequest().build();
    }


}
