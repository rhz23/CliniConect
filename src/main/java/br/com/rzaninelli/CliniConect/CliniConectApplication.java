package br.com.rzaninelli.CliniConect;

import br.com.rzaninelli.CliniConect.utils.consultaEstado.ConsultaEstadosAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CliniConectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CliniConectApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ConsultaEstadosAPI consultaEstadosAPI) {
		return args -> {
			consultaEstadosAPI.carregarEstados();
		};
	}
}
