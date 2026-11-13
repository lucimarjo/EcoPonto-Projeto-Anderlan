
package br.com.ecoponto.ecoponto_api.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HorarioDTO(

    @NotBlank(message = "O dia da semana é obrigatório")
    String diaSemana,

    @NotNull(message = "O horário é obrigatório")
    LocalTime horario,

    @NotBlank(message = "O bairro é obrigatório")
    String bairro,

    @NotBlank(message = "A rota é obrigatória")
    String rota,

    @NotNull(message = "O caminhão é obrigatório")
    Long caminhaoId

) {}
