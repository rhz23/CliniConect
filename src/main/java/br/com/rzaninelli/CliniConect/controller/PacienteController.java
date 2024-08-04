package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.model.Paciente;
import br.com.rzaninelli.CliniConect.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class PacienteController {

    @Autowired
    IPacienteService pacienteService;

    //TODO 03/08/2024 rhzan: Implement the GETMapping
    @GetMapping("/pacientes")
    public ResponseEntity<List<Paciente>> recuperarTodosPacientes() {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        if (!pacientes.isEmpty()) {
            return ResponseEntity.ok().body(pacientes);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/pacientes/busca")
    public ResponseEntity<List<Paciente>> buscarPacientePorNome(@RequestParam(name = "nome") String nome) {
        List<Paciente> pacientes = pacienteService.buscarPacientesPorNome(nome);
        if (!pacientes.isEmpty()) {
            return ResponseEntity.ok().body(pacientes);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable int id) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        if (paciente != null) {
            return ResponseEntity.ok().body(paciente);
        }
        return ResponseEntity.notFound().build();
    }

    //TODO 03/08/2024 rhzan: Implement the POSTMapping
    @PostMapping("/paciente")
    public ResponseEntity<Paciente> inserirPaciente(@RequestBody Paciente paciente) throws Exception {
        Paciente resultado = pacienteService.cadastrarPaciente(paciente);
        if (resultado != null) {
            return ResponseEntity.created(new URI("/pacientes/"+resultado.getIdPaciente())).body(resultado);
        }
        return ResponseEntity.badRequest().build();
    }

    //TODO 03/08/2024 rhzan: Implement the PUTMapping
    @PutMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> alterarPaciente(@PathVariable Integer id, @RequestBody Paciente paciente) throws Exception {
        if (id != null) {
            paciente.setIdPaciente(id);
        }
        Paciente pacienteAtual = pacienteService.buscarPacientePorId(id);
        if (pacienteAtual != null) {
            pacienteAtual = pacienteService.alterarPaciente(paciente);
            return ResponseEntity.ok().body(pacienteAtual);
        }
        return ResponseEntity.notFound().build();
    }

    //TODO 03/08/2024 rhzan: Implement the DELETMapping
}
