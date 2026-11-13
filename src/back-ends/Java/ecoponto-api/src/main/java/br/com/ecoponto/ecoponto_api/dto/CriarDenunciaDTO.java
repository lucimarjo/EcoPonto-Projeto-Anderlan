package br.com.ecoponto.ecoponto_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarDenunciaDTO(

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 1000)
    String descricao,

    @NotBlank(message = "O endereço é obrigatório")
    String endereco,

    Double latitude,

    Double longitude

) {}
