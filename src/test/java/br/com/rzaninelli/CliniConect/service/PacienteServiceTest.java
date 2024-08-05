package br.com.rzaninelli.CliniConect.service;

import br.com.rzaninelli.CliniConect.model.Paciente;
import br.com.rzaninelli.CliniConect.service.paciente.IPacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Sql("/import.sql")
public class PacienteServiceTest {

    @Autowired
    IPacienteService pacienteService;



    @Test
    public void CriarPacienteHappyDay() {
        String nome = "João";
        Paciente paciente = new Paciente();
        paciente.setNomePaciente(nome);
        paciente.setAtivoPaciente(false);
        Paciente resultadoPaciente = pacienteService.cadastrarPaciente(paciente);

        assertTrue(resultadoPaciente != null, "Retorno de cadastrarPaciente foi igual a NULL");
        assertTrue(resultadoPaciente.getAtivoPaciente().equals(true), "Paciente foi cadastrado como inativo");
        assertTrue(resultadoPaciente.getNomePaciente().equals(nome), "Nome do paciente diferente do esperado");
    }

    public void alterarPaciente() {
        //TODO 03/08/2024 rhzan:
    }

    @Test
    public void ListarPacientes() {
        //TODO 03/08/2024 rhzan:
        Sort ordenacao = Sort.by("nomePaciente").ascending();
        Pageable paginanaco = PageRequest.of(0, 5, ordenacao);

        List<Paciente> pacientes;

        pacientes = pacienteService.listarPacientes(paginanaco).getContent();
        assertTrue(pacientes.size() > 0, "Lista de pacientes retornou vazia");
        assertTrue(pacientes.size() == 5, "Lista de pacientes deveria retornar 5 pacientes");

        paginanaco = PageRequest.of(1, 5, ordenacao);
        pacientes = pacienteService.listarPacientes(paginanaco).getContent();
        assertTrue(pacientes.size() == 2, "Segunda pagina deveria conter somente 2 pacientes");

        paginanaco = PageRequest.of(2, 5, ordenacao);
        pacientes = pacienteService.listarPacientes(paginanaco).getContent();
        assertTrue(pacientes.isEmpty(), "Terceira Pagina reveria retornar vazia");
    }

    public void listarPacientePorIdy() {
        //TODO 03/08/2024 rhzan:
    }

    @Test
    public void listarPacientePorNome() {
        //TODO 03/08/2024 rhzan:
    }

    public void ListarPacientePorCpf() {
        //TODO 03/08/2024 rhzan:
    }

    public void ListarPacientePorEmail() {
        //TODO 03/08/2024 rhzan:
    }

    @Test
    public void ExcluirPacienteHappyDay() {
        Paciente paciente = new Paciente();
        paciente.setNomePaciente("Maria");
        Paciente pacienteCadastrado = pacienteService.cadastrarPaciente(paciente);
        Integer idPacienteCadastrado = pacienteCadastrado.getIdPaciente();

        assertTrue(pacienteService.deletarPaciente(idPacienteCadastrado).equals(paciente), "Não foi possivel excluir paciente cadastrado");
    }

    public void TentarExcluirPacienteInexistente() {
        //TODO 03/08/2024 rhzan:
    }



}
