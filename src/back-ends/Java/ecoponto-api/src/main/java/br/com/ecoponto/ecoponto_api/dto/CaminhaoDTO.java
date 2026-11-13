
package br.com.ecoponto.ecoponto_api.dto;

import jakarta.validation.constraints.NotBlank;

public record CaminhaoDTO(

    @NotBlank(message = "A identificação é obrigatória")
    String identificacao,

    @NotBlank(message = "A placa é obrigatória")
    String placa,

    Boolean ativo

) {}
