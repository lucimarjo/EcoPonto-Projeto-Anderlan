
package br.com.ecoponto.ecoponto_api.controller;

import br.com.ecoponto.ecoponto_api.dto.CadastroUsuarioDTO;
import br.com.ecoponto.ecoponto_api.dto.LoginDTO;
import br.com.ecoponto.ecoponto_api.dto.UsuarioDTO;
import br.com.ecoponto.ecoponto_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioDTO> cadastrar(
            @Valid @RequestBody CadastroUsuarioDTO dados) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.cadastrar(dados));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Valid @RequestBody LoginDTO dados) {

        String token = authService.login(dados);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
