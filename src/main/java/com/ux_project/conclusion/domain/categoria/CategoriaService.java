package com.ux_project.conclusion.domain.categoria;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria atualizarCategoria(Long id, Categoria novaCategoria) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNome(novaCategoria.getNome());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + id));
    }

    public void deletarCategoria(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }
    }
}
