
package br.com.ecoponto.ecoponto_api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping
    public Map<String, String> verificar() {
        return Map.of(
                "status", "online",
                "mensagem", "EcoPonto API está funcionando"
        );
    }
}
