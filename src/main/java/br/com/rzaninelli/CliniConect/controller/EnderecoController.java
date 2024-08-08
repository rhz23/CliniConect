package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.model.Endereco;
import br.com.rzaninelli.CliniConect.service.endereco.IEnderecoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@CrossOrigin("*")
public class EnderecoController {

    private static final Logger log = LoggerFactory.getLogger(EnderecoController.class);
    @Autowired
    IEnderecoService enderecoService;

    @GetMapping("/endereco/{id}")
    public ResponseEntity<Endereco> getEndereco(@PathVariable Integer id) {
        Endereco endereco = enderecoService.buscarEnderecoPorId(id);
        if (endereco != null) {
            return ResponseEntity.ok().body(endereco);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/endereco")
    public ResponseEntity<Endereco> inserirEndereco(@RequestBody Endereco endereco) {
        try {
            Endereco resultado = enderecoService.cadastrarEndereco(endereco);
            if (resultado != null) {
                return ResponseEntity.created(new URI("/endereco/"+resultado.getIdEndereco())).body(resultado);
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(endereco);
        }
    }

    @PutMapping("/endereco/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco endereco) {

        if (id != null) {
            endereco.setIdEndereco(id);
        }
        Endereco enderecoAtual = enderecoService.buscarEnderecoPorId(id);
        if (enderecoAtual != null) {
            enderecoAtual = enderecoService.atualizarEndereco(endereco);
            return ResponseEntity.ok().body(enderecoAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/endereco")
    public ResponseEntity<Endereco> deletarEndereco(@RequestParam Integer id) {
        Endereco endereco = enderecoService.buscarEnderecoPorId(id);
        if (endereco != null) {
            enderecoService.deletarEndereco(id);
            return ResponseEntity.ok().body(endereco);
        }
        return ResponseEntity.notFound().build();
    }
}
