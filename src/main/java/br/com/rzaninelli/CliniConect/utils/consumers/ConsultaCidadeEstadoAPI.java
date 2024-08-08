package br.com.rzaninelli.CliniConect.utils.consumers;

import br.com.rzaninelli.CliniConect.dto.CidadeResultadoDTO;
import br.com.rzaninelli.CliniConect.dto.EstadoResultDTO;
import br.com.rzaninelli.CliniConect.model.Cidade;
import br.com.rzaninelli.CliniConect.model.Estado;
import br.com.rzaninelli.CliniConect.service.cidade.ICidadeService;
import br.com.rzaninelli.CliniConect.service.estado.IEstadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
public class ConsultaCidadeEstadoAPI {

    private static final Logger log = LoggerFactory.getLogger(ConsultaCidadeEstadoAPI.class);
    @Autowired
    private IEstadoService estadoService;
    @Autowired
    private ICidadeService cidadeService;

    private String url = "https://servicodados.ibge.gov.br";

    //cron expression(second, minute, hour, day of month, month, day(s) of week)
    //* any

    @Scheduled(cron = "0 0 4 * * ?")
    @Async
    public void carregarEstados() {
        log.info("carregando estados");
        String uri = "/api/v1/localidades/estados";

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

    @Scheduled(cron = "0 0 4 * * ?")
    @Async
    public void carregarCidades() {
        log.info("carregando cidades");
        List<Estado> estados = estadoService.listarEstados();

        if (estados != null) {
            for (Estado estado : estados) {
                carregarCidadesPorEstado(estado);
            }
        }
    }


    public void carregarCidadesPorEstado(Estado estado) {
        String uri = "/api/v1/localidades/estados/"+ estado.getSiglaEstado() +"/municipios";

        Flux<CidadeResultadoDTO> resultado = WebClient
                .create(url)
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(CidadeResultadoDTO.class);

        List<CidadeResultadoDTO> listaCidadesUF = resultado.collectList().block();

        if (listaCidadesUF != null) {
            for (CidadeResultadoDTO cidade : listaCidadesUF) {
                Cidade cidadeTemp = new Cidade();
                cidadeTemp.setIdCidade(cidade.getId());
                cidadeTemp.setNomeCidade(cidade.getNome());
                cidadeTemp.setEstado(estado);

                cidadeService.cadastrarCidade(cidadeTemp);
            }
        }
    }
}
