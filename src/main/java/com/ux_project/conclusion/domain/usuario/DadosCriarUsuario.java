package com.ux_project.conclusion.domain.usuario;

public record DadosCriarUsuario(
        Long id,
        String nome,
        String login,
        String email,
        String senha,
        String tipo
) {
}
