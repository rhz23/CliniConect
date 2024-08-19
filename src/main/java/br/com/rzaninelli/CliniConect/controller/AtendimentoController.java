package br.com.rzaninelli.CliniConect.controller;

import br.com.rzaninelli.CliniConect.model.Atendimento;
import br.com.rzaninelli.CliniConect.service.Atendimento.IAtendimentoService;
import br.com.rzaninelli.CliniConect.service.paciente.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
public class AtendimentoController {
    //TODO 04/08/2024 rhzan:

    private static final Logger log = LoggerFactory.getLogger(AtendimentoController.class);

    @Autowired
    IAtendimentoService atendimentoService;

    @Autowired
    IPacienteService pacienteService;

    @GetMapping("/atendimento/{id}")
    public ResponseEntity<Atendimento> getAtendimento(@PathVariable Integer id) {
        Atendimento atendimento = atendimentoService.buscarAtendimentoPorId(id);
        if (atendimento != null) {
            return ResponseEntity.ok().body(atendimento);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/paciente/{id}/atendimento")
    public ResponseEntity<List<Atendimento>> getAtendimentoPaciente(@PathVariable Integer id) {
        try {
            List<Atendimento> atendimentos = atendimentoService.listarAtendimentosPorPaciente(id);
            if (!atendimentos.isEmpty()) {
                return ResponseEntity.ok().body(atendimentos);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/paciente/{id}/atendimento")
    public ResponseEntity<Atendimento> inserirAtendimento(@PathVariable Integer id, @RequestBody Atendimento atendimento) {
        try {
            Atendimento resultado = atendimentoService.cadastrarAtendimento(atendimento, id);
            if (resultado != null) {
                return ResponseEntity.created(new URI("/atendimento/"+resultado.getIdAtendimento())).body(resultado);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(atendimento);
        }
    }

    @PutMapping("/paciente/{id}/atendimento/{idAtendimento}")
    public ResponseEntity<Atendimento> atualizarAtendimento(@PathVariable Integer id, @RequestBody Atendimento atendimento, @PathVariable Integer idAtendimento) {

        //TODO 13/08/2024 rhzan: Refactor because of multiple badRequest
        if (id == null || idAtendimento == null) {
            return ResponseEntity.badRequest().build();
        }
        Atendimento novoAtendimento = atendimentoService.buscarAtendimentoPorId(id);
        if (novoAtendimento != null) {
            if (Objects.equals(novoAtendimento.getPaciente().getIdPaciente(), id)) {
                if (atendimento.getInfoAtendimento() == null) {
                    novoAtendimento.setInfoAtendimento(atendimento.getInfoAtendimento());
                    novoAtendimento = atendimentoService.atualizarAtendimento(novoAtendimento);
                    return ResponseEntity.ok().body(novoAtendimento);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/atendimento/{id}")
    public ResponseEntity<?> deletarAtendimento(@PathVariable Integer id) {
        if (atendimentoService.removerAtendimento(id)) {
            return ResponseEntity.ok("Excluido com sucesso!");
        }
        return ResponseEntity.notFound().build();
    }
}
