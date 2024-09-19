package SGP_Backend.SGP_Backend.service;

import SGP_Backend.SGP_Backend.controller.response.ProjetoResponse;
import SGP_Backend.SGP_Backend.enuns.StatusProjeto;
import SGP_Backend.SGP_Backend.enuns.TipoProjeto;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoException;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import SGP_Backend.SGP_Backend.model.Projeto;
import SGP_Backend.SGP_Backend.repository.ProjetoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjetoService {

    private final ProjetoRepository repository;

    public List<ProjetoResponse> buscarTodosProjetosPorStatusETipo(TipoProjeto tipo, StatusProjeto status) {
        List<Projeto> projetos = repository.findAllByTipoAndStatus(tipo, status);
        return ProjetoResponse.converteProjetos(projetos);
    }
    public Projeto buscarProjetoPorId(Long id) {
        try {
            return repository.findById(id).get();
        }catch (Exception e) {
            throw new ProjetoNaoEncontradoException("Projeto não encontrado.");
        }
    }
    public Projeto salvarProjeto(Long id, Projeto projetoRequest) {
        Optional<Projeto> projeto = repository.findById(id);
        if (projeto.isEmpty()){
            throw new ProjetoNaoEncontradoException("Projeto não encontrado.");
        }
        return repository.save(projetoRequest);
    }
}