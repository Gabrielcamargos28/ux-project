package com.ux_project.conclusion.domain.doacao;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    public Doacao salvarDoacao(Doacao doacao) {
        return doacaoRepository.save(doacao);
    }

    public List<Doacao> buscarTodas() {
        return doacaoRepository.findAll();
    }

    public Optional<Doacao> buscarPorId(Long id) {
        return doacaoRepository.findById(id);
    }

    public Doacao atualizarDoacao(Long id, Doacao novaDoacao) {
        return doacaoRepository.findById(id)
                .map(doacao -> {
                    doacao.setCategoria(novaDoacao.getCategoria());
                    doacao.setQuantidade(novaDoacao.getQuantidade());
                    doacao.setDataDaRetirada(novaDoacao.getDataDaRetirada());
                    doacao.setEnderecoRetirada(novaDoacao.getEnderecoRetirada());
                    return doacaoRepository.save(doacao);
                })
                .orElseThrow(() -> new RuntimeException("Doação não encontrada com o ID: " + id));
    }

    public void deletarDoacao(Long id) {
        if (doacaoRepository.existsById(id)) {
            doacaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Doação não encontrada com o ID: " + id);
        }
    }
}
