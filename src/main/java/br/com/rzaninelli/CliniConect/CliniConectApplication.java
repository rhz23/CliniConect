package br.com.rzaninelli.CliniConect;

import br.com.rzaninelli.CliniConect.utils.consumers.ConsultaCidadeEstadoAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CliniConectApplication {

	private static Logger log = LoggerFactory.getLogger(CliniConectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CliniConectApplication.class, args);
	}

	@Bean
	@Profile("prod")
	CommandLineRunner init(ConsultaCidadeEstadoAPI consultaCidadeEstadosAPI) {
		return args -> {
			consultaCidadeEstadosAPI.carregarEstados();
			log.info("Carregando/atualizando Estados");
//			consultaCidadeEstadosAPI.carregarCidades();
//			log.info("Carregando/atualizando Cidades");
		};
	}
}
