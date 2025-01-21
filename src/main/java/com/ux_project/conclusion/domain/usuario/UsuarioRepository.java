package com.ux_project.conclusion.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);

}
