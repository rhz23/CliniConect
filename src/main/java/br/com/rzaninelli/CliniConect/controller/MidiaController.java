package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.model.Midia;
import br.com.rzaninelli.CliniConect.service.midia.IMidiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class MidiaController {

    @Autowired
    private IMidiaService midiaService;

    @GetMapping("/midias/{id}")
    public ResponseEntity<Midia> buscarMidiaPeloId(@PathVariable int id) {
        Midia midia = midiaService.buscarPeloId(id);
        if (midia != null) {
            return ResponseEntity.ok(midia);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/midias")
    public ResponseEntity<Midia> adicionarMidia(@RequestBody Midia midia) {
        Midia resultado = midiaService.cadastraMidia(midia);
        if (resultado != null) {
            return ResponseEntity.status(201).body(resultado);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/midias/{id}")
    public ResponseEntity<Midia> alterarMidia(@RequestBody Midia midia, @PathVariable int id) {
        if (midia.getNumSequencial() == null) {
            midia.setNumSequencial(id);
        }
        Midia resultado = midiaService.atualizaMidia(midia);
        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/midias/{id}")
    public ResponseEntity<?> excluirMidia(@PathVariable int id) {
        if (midiaService.excluiMidia(id)) {
            return ResponseEntity.ok("Excluido com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }
}
