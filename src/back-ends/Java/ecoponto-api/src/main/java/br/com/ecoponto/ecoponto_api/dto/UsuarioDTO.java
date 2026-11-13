
package br.com.ecoponto.ecoponto_api.dto;

import br.com.ecoponto.ecoponto_api.enums.PerfilUsuario;

public record UsuarioDTO(
    Long id,
    String nome,
    String email,
    PerfilUsuario perfil
) {}
