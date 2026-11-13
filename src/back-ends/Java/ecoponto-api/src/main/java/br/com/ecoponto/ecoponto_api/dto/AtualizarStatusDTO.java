package br.com.ecoponto.ecoponto_api.dto;

import br.com.ecoponto.ecoponto_api.enums.StatusDenuncia;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusDTO(

    @NotNull(message = "O status é obrigatório")
    StatusDenuncia status

) {}
