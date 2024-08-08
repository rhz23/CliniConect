package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.model.Cidade;
import br.com.rzaninelli.CliniConect.service.cidade.ICidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CidadeController {

    @Autowired
    ICidadeService cidadeService;

    @GetMapping("/cidades")
    public ResponseEntity<List<Cidade>> listarCidades() {
        List<Cidade> cidades = cidadeService.listarCidades();
        if (cidades != null) {
            return ResponseEntity.ok().body(cidades);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cidades/{id}")
    public Cidade buscarCidade(@PathVariable int id) {
        return cidadeService.buscarCidadePorId(id);
    }
}
