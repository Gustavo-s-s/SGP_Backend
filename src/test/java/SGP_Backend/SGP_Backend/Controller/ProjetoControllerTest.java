package SGP_Backend.SGP_Backend.Controller;

import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import SGP_Backend.SGP_Backend.model.Equipe;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.model.Unidade;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import SGP_Backend.SGP_Backend.utils.ProjetoFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jacksonObjectMapper;
    @Autowired
    private ProjetoRepository repository;

    @BeforeEach
    public void setup() {
        List<Projeto> projetos = getProjetosExemplo();
        repository.saveAll(projetos);
    }

    @Test
    public void deveRetornarListaProjetos() throws Exception {
        mockMvc.perform(get("/projetos")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nome").value("Nome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].objetivo").value("objetivo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dataInicio").value("2020-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dataFim").value("2022-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].prazo").value("2021-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].tipo").value("ESTRATEGICO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value("FINALIZADO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].nome").value("Nome2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].objetivo").value("objetivo2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].dataInicio").value("2020-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].dataFim").value("2022-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].prazo").value("2021-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].tipo").value("TATICO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].status").value("EM_ANDAMENTO"));

    }

    @Test
    public void deveRetornarListaProjetosFiltradoPorTipo() throws Exception {
        mockMvc.perform(get("/projetos?tipo=ESTRATEGICO")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nome").value("Nome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].objetivo").value("objetivo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dataInicio").value("2020-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dataFim").value("2022-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].prazo").value("2021-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].tipo").value("ESTRATEGICO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value("FINALIZADO"));
    }

    @Test
    public void deveRetornarListaProjetosFiltradoPorStatus() throws Exception {
        mockMvc.perform(get("/projetos?status=FINALIZADO")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nome").value("Nome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].objetivo").value("objetivo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dataInicio").value("2020-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dataFim").value("2022-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].prazo").value("2021-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].tipo").value("ESTRATEGICO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value("FINALIZADO"));
    }

    @Test
    public void deveRetornarListaProjetosFiltradoPorTipoEStatus() throws Exception {
        mockMvc.perform(get("/projetos?tipo=TATICO&status=EM_ANDAMENTO")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nome").value("Nome2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].objetivo").value("objetivo2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dataInicio").value("2020-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].dataFim").value("2022-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].prazo").value("2021-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].tipo").value("TATICO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value("EM_ANDAMENTO"));
    }

    @Test
    public void deveRetornarProjetoPorId() throws Exception {
        mockMvc.perform(get("/projetos/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Nome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.objetivo").value("objetivo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataInicio").value("2020-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataFim").value("2022-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prazo").value("2021-08-11T14:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipo").value("ESTRATEGICO"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("FINALIZADO"));
    }

    @Test
    public void deveLancarExcecaoQuandoEntidadeNaoForEncontradaAoBuscarProjetoPorId() throws Exception {
        mockMvc.perform(get("/projetos/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProjetoNaoEncontradoException))
                .andExpect(result -> assertEquals("Projeto não encontrado.", result.getResolvedException().getMessage()));
    }

    @Test
    public void deveLancarExcecaoQuandoTentarListarProjetosPorTipoInexistente() throws Exception {
        ResultActions requestResult = mockMvc.perform(get("/projetos?tipo=NORMAL")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }

    @Test
    public void deveLancarExcecaoQuandoTentarListarProjetosPorStatusInexistente() throws Exception {
        ResultActions requestResult = mockMvc.perform(get("/projetos?status=TERMINADO")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }

    @Test
    public void deveLancarExcecaoQuandoEntidadeNaoForEncontradaAoAtualizarProjeto() throws Exception {
        mockMvc.perform(put("/projetos/999")
                        .content(jacksonObjectMapper.writeValueAsString(new Projeto()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProjetoNaoEncontradoException))
                .andExpect(result -> assertEquals("Projeto não encontrado.", result.getResolvedException().getMessage()));
    }

    private List<Projeto> getProjetosExemplo(){
        Projeto projeto1 = ProjetoFactory.buildProjeto().build();

        Projeto projeto2 = ProjetoFactory
                .buildProjeto()
                .id(2L)
                .nome("Nome2")
                .versao("Versao2")
                .objetivo("objetivo2")
                .tipo(TipoProjeto.TATICO)
                .status(StatusProjeto.EM_ANDAMENTO)
                .build();

        List<Projeto> projetos = new ArrayList<>();
        projetos.add(projeto1);
        projetos.add(projeto2);
        return projetos;
    }
}