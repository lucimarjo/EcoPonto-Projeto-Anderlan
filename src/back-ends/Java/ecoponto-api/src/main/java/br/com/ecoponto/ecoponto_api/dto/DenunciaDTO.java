
package br.com.ecoponto.ecoponto_api.dto;

import java.time.LocalDateTime;

import br.com.ecoponto.ecoponto_api.enums.StatusDenuncia;

public record DenunciaDTO(
    Long id,
    String descricao,
    String endereco,
    Double latitude,
    Double longitude,
    StatusDenuncia status,
    LocalDateTime dataCriacao,
    Long usuarioId,
    String nomeUsuario
) {}
