package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.model.Paciente;
import br.com.rzaninelli.CliniConect.service.paciente.IPacienteService;
import br.com.rzaninelli.CliniConect.utils.Validador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class PacienteController {

    private static final Logger log = LoggerFactory.getLogger(PacienteController.class);

    //TODO 13/08/2024 rhzan: Refactor!
    @Autowired
    IPacienteService pacienteService;


    @GetMapping("/pacientes")
    public ResponseEntity<Map<String, Object>> recuperarTodosPacientes(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "5") int tamanho, @RequestParam(defaultValue = "nomePaciente") String atributo) {
        try {
            Sort ordenacao = Sort.by(atributo).ascending();
            Pageable paginanaco = PageRequest.of(pagina, tamanho, ordenacao);
            Page<Paciente> paginaPacientes = pacienteService.listarPacientes(paginanaco);

            if (!paginaPacientes.isEmpty()) {

                //TODO 13/08/2024 rhzan: Refactor since used more than once
                Map<String, Object> response = new HashMap<>();
                response.put("Paciente", paginaPacientes.getContent());
                response.put("pagina", paginaPacientes.getNumber());
                response.put("tamanho", paginaPacientes.getTotalElements());
                response.put("paginasTotais", paginaPacientes.getTotalPages());

                return ResponseEntity.ok().body(response);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            log.error("Erro ao recuperar lista de pacientes do banco de dados");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //TODO 04/08/2024 rhzan: Alterar o retorno para paginado
    @GetMapping("/pacientes/busca")
    public ResponseEntity<Map<String, Object>> buscarPacientePorNome(@RequestParam(name = "nome") String nome, @RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "5") int tamanho) {
        Pageable paginanaco = PageRequest.of(pagina, tamanho);
        Page<Paciente> paginaPacientes = pacienteService.buscarPacientesPorNome(nome, paginanaco);
        if (paginaPacientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("Paciente", paginaPacientes.getContent());
        response.put("pagina", paginaPacientes.getNumber());
        response.put("tamanho", paginaPacientes.getTotalElements());
        response.put("paginasTotais", paginaPacientes.getTotalPages());

        return ResponseEntity.ok().body(response);
    }

    //TODO 04/08/2024 rhzan: add tratamento de exceção
    @GetMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable int id) {
        Paciente paciente = pacienteService.buscarPacientePorId(id);
        if (paciente != null) {
            return ResponseEntity.ok().body(paciente);
        }
        return ResponseEntity.notFound().build();
    }

    //TODO 03/08/2024 rhzan: Implement the POSTMapping
    @PostMapping("/pacientes")
    public ResponseEntity<Paciente> inserirPaciente(@RequestBody Paciente paciente) throws Exception {

        if (!paciente.getCpfPaciente().isEmpty() && !Validador.validarCPF(paciente.getCpfPaciente())){
            log.info("CPF Invalido");
            return ResponseEntity.badRequest().build();
        }

        if (!paciente.getEmailPaciente().isEmpty() && !Validador.validarEmail(paciente.getEmailPaciente())){
            log.info("Email Invalido");
            return ResponseEntity.badRequest().build();
        }

        Paciente resultado = pacienteService.cadastrarPaciente(paciente);
        if (resultado != null) {
            return ResponseEntity.created(new URI("/pacientes/"+resultado.getIdPaciente())).body(resultado);
        }
        return ResponseEntity.badRequest().build();
    }

    //TODO 03/08/2024 rhzan: add tratamento de exceção
    @PutMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> alterarPaciente(@PathVariable Integer id, @RequestBody Paciente paciente) {
        if (id != null) {
            paciente.setIdPaciente(id);
        }

        if (!paciente.getCpfPaciente().isEmpty() && !Validador.validarCPF(paciente.getCpfPaciente())){
            log.info("CPF Invalido");
            return ResponseEntity.badRequest().build();
        }

        if (!paciente.getEmailPaciente().isEmpty() && !Validador.validarEmail(paciente.getEmailPaciente())){
            log.info("Email Invalido");
            return ResponseEntity.badRequest().build();
        }

        Paciente pacienteAtual = pacienteService.buscarPacientePorId(id);
        if (pacienteAtual != null) {
            pacienteAtual = pacienteService.alterarPaciente(paciente);
            return ResponseEntity.ok().body(pacienteAtual);
        }
        return ResponseEntity.notFound().build();
    }

    //TODO 03/08/2024 rhzan: add tratamento de exceção
    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<Paciente> excluirPaciente(@PathVariable Integer id) {
        Paciente paciente = pacienteService.deletarPaciente(id);
        if (paciente != null) {
            return ResponseEntity.ok().body(paciente);
        }
        return ResponseEntity.notFound().build();
    }
}
