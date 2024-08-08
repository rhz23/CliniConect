package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.model.Estado;
import br.com.rzaninelli.CliniConect.service.estado.IEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class EstadoController {


    @Autowired
    IEstadoService estadoService;

    @GetMapping("/estados")
    public ResponseEntity<List<Estado>> listarEstados() {
        List<Estado> estados = estadoService.listarEstados();
        if (estados != null) {
            return ResponseEntity.ok().body(estados);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estados/{id}")
    public Estado buscarEstado(@PathVariable Integer id) {
        return estadoService.buscarEstadoPorId(id);
    }
}
