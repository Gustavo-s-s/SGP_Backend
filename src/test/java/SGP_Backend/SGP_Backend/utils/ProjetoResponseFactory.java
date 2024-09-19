package SGP_Backend.SGP_Backend.utils;

import SGP_Backend.SGP_Backend.controller.response.ProjetoResponse;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.Projeto;

import java.time.LocalDateTime;
import java.util.Set;

public class ProjetoResponseFactory {
    public static ProjetoResponse.ProjetoResponseBuilder buildProjetoResponse(){
        return ProjetoResponse.builder()
                .id(1L)
                .nome("Nome")
                .objetivo("objetivo")
                .dataInicio(LocalDateTime.of(2020, 8, 11, 14, 30))
                .dataFim(LocalDateTime.of(2022, 8, 11, 14, 30))
                .prazo(LocalDateTime.of(2021, 8, 11, 14, 30))
                .tipo(TipoProjeto.ESTRATEGICO)
                .status(StatusProjeto.FINALIZADO);
    }
}
