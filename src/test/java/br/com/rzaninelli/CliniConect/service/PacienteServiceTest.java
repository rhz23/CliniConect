package br.com.rzaninelli.CliniConect.service;

import br.com.rzaninelli.CliniConect.model.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    public void alterarPacienteHappyDay() {
        //TODO 03/08/2024 rhzan:
    }

    public void ListarPacientesHappyDay() {
        //TODO 03/08/2024 rhzan:
    }

    public void listarPacientePorIdHappyDay() {
        //TODO 03/08/2024 rhzan:
    }

    @Test
    public void listarPacientePorNomeHappyDay() {
        //TODO 03/08/2024 rhzan:
        List<Paciente> listaPacientes = pacienteService.buscarPacientesPorNome("a");
        assertTrue(listaPacientes.size() > 0, "Não retornou nenhum paciente quando deveria retornar 5");


    }

    public void ListarPacientePorCpfHappyDay() {
        //TODO 03/08/2024 rhzan:
    }

    public void ListarPacientePorEmailHappyDay() {
        //TODO 03/08/2024 rhzan:
    }

    @Test
    public void ExcluirPacienteHappyDay() {
        Paciente paciente = new Paciente();
        paciente.setNomePaciente("Maria");
        Paciente pacienteCadastrado = pacienteService.cadastrarPaciente(paciente);
        Integer idPacienteCadastrado = pacienteCadastrado.getIdPaciente();

//        assertTrue(pacienteService.deletarPaciente(idPacienteCadastrado), "Não foi possivel excluir paciente cadastrado");
    }

    public void TentarExcluirPacienteInexistente() {
        //TODO 03/08/2024 rhzan:
    }



}
