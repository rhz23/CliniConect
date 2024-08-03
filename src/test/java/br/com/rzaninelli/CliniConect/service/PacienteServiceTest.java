package br.com.rzaninelli.CliniConect.service;

import br.com.rzaninelli.CliniConect.model.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
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

    public void tentarAlterarPaciente() {
        //TODO 03/08/2024 rhzan:
    }

    public void tentarListarPacientes() {
        //TODO 03/08/2024 rhzan:
    }

    public void tentarlistarPacientePorId() {
        //TODO 03/08/2024 rhzan:
    }

    public void tentarlistarPacientePorNome() {
        //TODO 03/08/2024 rhzan:
    }

    public void tentarListarPacientePorCpf() {
        //TODO 03/08/2024 rhzan:
    }

    public void tentarListarPacientePorEmail() {
        //TODO 03/08/2024 rhzan:
    }

    @Test
    public void tentarExcluirPaciente() {
        Paciente paciente = new Paciente();
        paciente.setNomePaciente("Maria");
        Paciente pacienteCadastrado = pacienteService.cadastrarPaciente(paciente);
        Integer idPacienteCadastrado = pacienteCadastrado.getIdPaciente();

        assertTrue(pacienteService.deletarPaciente(idPacienteCadastrado), "Não foi possivel excluir paciente cadastrado");
    }



}
