package com.ux_project.conclusion.domain.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Usuario salvarUsuario(Usuario usuario) {

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario novoUsuario) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(novoUsuario.getNome());
                    usuario.setLogin(novoUsuario.getLogin());
                    usuario.setEmail(novoUsuario.getEmail());
                    usuario.setTipo(novoUsuario.getTipo());
                    // Atualizar senha, se fornecida
                    if (novoUsuario.getSenha() != null && !novoUsuario.getSenha().isBlank()) {
                        usuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
                    }
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }

    public void deletarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
    }
}
