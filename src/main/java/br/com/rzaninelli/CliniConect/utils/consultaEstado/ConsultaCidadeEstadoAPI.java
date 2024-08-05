package br.com.rzaninelli.CliniConect.utils.consultaEstado;

import br.com.rzaninelli.CliniConect.dto.EstadoResultDTO;
import br.com.rzaninelli.CliniConect.model.Estado;
import br.com.rzaninelli.CliniConect.service.estado.IEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
public class ConsultaCidadeEstadoAPI {

    @Autowired
    private IEstadoService estadoService;

    private String url = "https://servicodados.ibge.gov.br";
    private String uri = "/api/v1/localidades/estados";

    //cron expression(second, minute, hour, day of month, month, day(s) of week)
    //* any

    @Scheduled(cron = "0 0 4 * * ?")
    @Async
    public void carregarEstados() {

        Flux<EstadoResultDTO> resultado = WebClient
                .create(url)
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(EstadoResultDTO.class);

        List<EstadoResultDTO> listaEstados = resultado.collectList().block();

        if (listaEstados != null) {
            for (EstadoResultDTO estado : listaEstados) {
                Estado estadoTemp = new Estado();
                estadoTemp.setIdEstado(estado.getId());
                estadoTemp.setNomeEstado(estado.getNome());
                estadoTemp.setSiglaEstado(estado.getSigla());

                estadoService.cadastrarEstado(estadoTemp);
            }
        }



    }
}
