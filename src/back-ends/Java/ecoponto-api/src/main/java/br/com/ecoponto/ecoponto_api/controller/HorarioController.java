
package br.com.ecoponto.ecoponto_api.controller;

import br.com.ecoponto.ecoponto_api.dto.HorarioDTO;
import br.com.ecoponto.ecoponto_api.model.Horario;
import br.com.ecoponto.ecoponto_api.service.HorarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Horario> criar(
            @Valid @RequestBody HorarioDTO dados) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(horarioService.criar(dados));
    }

    @GetMapping
    public ResponseEntity<List<Horario>> listarTodos() {
        return ResponseEntity.ok(horarioService.listarTodos());
    }

    @GetMapping("/bairro/{bairro}")
    public ResponseEntity<List<Horario>> buscarPorBairro(
            @PathVariable String bairro) {

        return ResponseEntity.ok(
                horarioService.buscarPorBairro(bairro)
        );
    }

    @GetMapping("/dia/{dia}")
    public ResponseEntity<List<Horario>> buscarPorDia(
            @PathVariable String dia) {

        return ResponseEntity.ok(
                horarioService.buscarPorDia(dia)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Horario> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody HorarioDTO dados) {

        return ResponseEntity.ok(
                horarioService.atualizar(id, dados)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        horarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
