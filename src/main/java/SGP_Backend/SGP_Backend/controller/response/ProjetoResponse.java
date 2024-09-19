package SGP_Backend.SGP_Backend.controller.response;

import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.Projeto;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@JsonSerialize
@EqualsAndHashCode
@Builder
public class ProjetoResponse {
    private Long id;
    private String nome;
    private String objetivo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private LocalDateTime prazo;
    private TipoProjeto tipo;
    private StatusProjeto status;

    public static List<ProjetoResponse> converteProjetos(List<Projeto> projetos){
        return projetos.stream().map(projeto ->{
                    return ProjetoResponse.builder()
                            .id(projeto.getId())
                            .nome(projeto.getNome())
                            .objetivo(projeto.getObjetivo())
                            .dataInicio(projeto.getDataInicio())
                            .dataFim(projeto.getDataFim())
                            .prazo(projeto.getPrazo())
                            .tipo(projeto.getTipo())
                            .status(projeto.getStatus())
                            .build();
                    }
        ).toList();
    }

}
