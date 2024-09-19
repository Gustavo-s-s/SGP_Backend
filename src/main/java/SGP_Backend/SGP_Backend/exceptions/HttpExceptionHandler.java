package SGP_Backend.SGP_Backend.exceptions;

import SGP_Backend.SGP_Backend.exceptions.dto.ApiErrorDTO;
import SGP_Backend.SGP_Backend.exceptions.projeto.ProjetoNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class HttpExceptionHandler {

    public static final String MENSAGEM_ERRO = " ERRO: ";
    public static final String OPERACAO = "OPERACAO";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> handleExcecaoNaoMapeada(Exception excecao) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ApiErrorDTO.builder()
                        .statusCode(500)
                        .error("Erro interno no servidor.")
                        .messages(null)
                        .detail(excecao.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorDTO> handleValorNaoContidoNoEnum(MethodArgumentTypeMismatchException excecao) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(ApiErrorDTO.builder()
                        .statusCode(400)
                        .error("O valor: "+excecao.getValue()+" não é valido para o campo "+excecao.getName())
                        .messages(null)
                        .detail(excecao.getMessage())
                        .build());
    }

    @ExceptionHandler(ProjetoNaoEncontradoException.class)
    public ResponseEntity<ApiErrorDTO> handleProjetoNaoEncontrado(ProjetoNaoEncontradoException excecao) {
        //log.error(MENSAGEM_ERRO, excecao);
        return ResponseEntity.status(NOT_FOUND)
                .body(ApiErrorDTO.builder()
                        .statusCode(404)
                        .error(excecao.getMessage())
                        .messages(null)
                        .detail(excecao.getMessage())
                        .build());
    }
}
