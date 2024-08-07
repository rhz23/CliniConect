package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.model.Usuario;
import br.com.rzaninelli.CliniConect.security.CliniToken;
import br.com.rzaninelli.CliniConect.service.usuario.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario resultado = authService.criarUsuario(usuario);
        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<CliniToken> login(@RequestBody Usuario dadosLogin) {
        CliniToken token = authService.realizarLogin(dadosLogin);
        if (token != null) {
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(403).build();
    }

}
