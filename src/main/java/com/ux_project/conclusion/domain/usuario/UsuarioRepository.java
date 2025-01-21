package com.ux_project.conclusion.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);

    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE LOWER(p.nome) = LOWER(:perfil)")
    List<Usuario> buscarPerfilIgnoreCase(@Param("perfil") String perfil);

    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.nome = 'professor'")
    Page<Usuario> findAllProfessores(Pageable pageable);

    @Query("SELECT u FROM Usuario u JOIN u.perfis p WHERE p.area.id = :areaId")
    List<Usuario> findUsuariosByAreaId(@Param("areaId") Long areaId);
}
