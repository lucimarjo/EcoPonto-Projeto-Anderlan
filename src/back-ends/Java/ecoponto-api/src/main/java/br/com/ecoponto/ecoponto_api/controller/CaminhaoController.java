
package br.com.ecoponto.ecoponto_api.controller;

import br.com.ecoponto.ecoponto_api.dto.CaminhaoDTO;
import br.com.ecoponto.ecoponto_api.model.Caminhao;
import br.com.ecoponto.ecoponto_api.service.CaminhaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caminhoes")
public class CaminhaoController {

    private final CaminhaoService caminhaoService;

    public CaminhaoController(CaminhaoService caminhaoService) {
        this.caminhaoService = caminhaoService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Caminhao> criar(
            @Valid @RequestBody CaminhaoDTO dados) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(caminhaoService.criar(dados));
    }

    @GetMapping
    public ResponseEntity<List<Caminhao>> listarTodos() {
        return ResponseEntity.ok(caminhaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caminhao> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                caminhaoService.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Caminhao> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CaminhaoDTO dados) {

        return ResponseEntity.ok(
                caminhaoService.atualizar(id, dados)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        caminhaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
