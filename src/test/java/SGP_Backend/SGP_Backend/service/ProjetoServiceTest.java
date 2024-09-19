package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.controller.response.ProjetoResponse;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import SGP_Backend.SGP_Backend.utils.ProjetoFactory;
import SGP_Backend.SGP_Backend.utils.ProjetoResponseFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarProjetoResponseAoBuscarProjetos() {

        when(projetoRepository.findAllByTipoAndStatus(null, null)).thenReturn(List.of(projetoExemplo()));

        List<ProjetoResponse> projetoResponses = projetoService.buscarTodosProjetosPorStatusETipo(null, null);

        assertNotNull(projetoResponses);
        assertEquals(projetoResponses.get(0), responseExemplo());
    }

    private Projeto projetoExemplo(){
        return ProjetoFactory.buildProjeto().build();
    }

    private ProjetoResponse responseExemplo() {
        return ProjetoResponseFactory.buildProjetoResponse().build();
    }

}