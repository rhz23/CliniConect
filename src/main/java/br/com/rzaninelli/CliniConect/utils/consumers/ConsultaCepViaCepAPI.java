package br.com.rzaninelli.CliniConect.utils.consumers;

import br.com.rzaninelli.CliniConect.dto.CepResultadoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ConsultaCepViaCepAPI {

    private static final Logger log = LoggerFactory.getLogger(ConsultaCidadeEstadoAPI.class);

    private String url = "viacep.com.br/ws/";
    private String uri;

    public CepResultadoDTO recuperarDadosCep(String cep) {
        uri = url + cep + "/json/";

        if (cep.length() == 8) {


            log.info("Recuperando dados de CEP");

            CepResultadoDTO resultadoCEP = WebClient
                    .create(url)
                    .get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(CepResultadoDTO.class).block();

            return resultadoCEP;
        }
        return null;
    }
}
