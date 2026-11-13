
package br.com.ecoponto.ecoponto_api.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> tratarStatus(
            ResponseStatusException exception) {

        Map<String, Object> resposta = new HashMap<>();

        resposta.put("data", LocalDateTime.now());
        resposta.put("status", exception.getStatusCode().value());
        resposta.put("erro", exception.getReason());

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(resposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacao(
            MethodArgumentNotValidException exception) {

        Map<String, String> erros = new HashMap<>();

        for (FieldError erro : exception.getBindingResult().getFieldErrors()) {
            erros.put(erro.getField(), erro.getDefaultMessage());
        }

        Map<String, Object> resposta = new HashMap<>();

        resposta.put("data", LocalDateTime.now());
        resposta.put("status", 400);
        resposta.put("erros", erros);

        return ResponseEntity
                .badRequest()
                .body(resposta);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> tratarErroGeral(
            Exception exception) {

        Map<String, Object> resposta = new HashMap<>();

        resposta.put("data", LocalDateTime.now());
        resposta.put("status", 500);
        resposta.put("erro", "Ocorreu um erro interno no servidor");

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(resposta);
    }
}
