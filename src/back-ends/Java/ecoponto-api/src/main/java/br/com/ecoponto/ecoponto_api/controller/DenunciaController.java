package br.com.ecoponto.ecoponto_api.controller;

import br.com.ecoponto.ecoponto_api.dto.AtualizarStatusDTO;
import br.com.ecoponto.ecoponto_api.dto.CriarDenunciaDTO;
import br.com.ecoponto.ecoponto_api.dto.DenunciaDTO;
import br.com.ecoponto.ecoponto_api.service.DenunciaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
public class DenunciaController {

    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DenunciaDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DenunciaDTO> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DenunciaDTO> cadastrar(
            @Valid @RequestBody CriarDenunciaDTO dados,
            @RequestParam Long usuarioId) {

        DenunciaDTO nova = service.criar(dados, usuarioId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nova);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DenunciaDTO> atualizarStatus(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarStatusDTO dados) {

        return ResponseEntity.ok(
                service.atualizarStatus(id, dados)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}