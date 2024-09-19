package SGP_Backend.SGP_Backend.controller;

import SGP_Backend.SGP_Backend.controller.response.ProjetoResponse;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService service;

    @GetMapping
    public ResponseEntity<List<ProjetoResponse>> buscarTodos(@RequestParam(value = "tipo", required = false) TipoProjeto tipo,
                                                             @RequestParam(value = "status", required = false) StatusProjeto status) {
        return ResponseEntity.ok(service.buscarTodosProjetosPorStatusETipo(tipo, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarProjetoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Long id, @RequestBody Projeto projeto) {
        return ResponseEntity.ok(service.salvarProjeto(id, projeto));
    }
}