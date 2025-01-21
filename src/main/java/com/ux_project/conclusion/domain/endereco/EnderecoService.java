package com.ux_project.conclusion.domain.endereco;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }


    public Endereco salvarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }


    public List<Endereco> buscarTodos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }


    public Endereco atualizarEndereco(Long id, Endereco novoEndereco) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    endereco.setRua(novoEndereco.getRua());
                    endereco.setNumero(novoEndereco.getNumero());
                    endereco.setBairro(novoEndereco.getBairro());
                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com o ID: " + id));
    }

    public void deletarEndereco(Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Endereço não encontrado com o ID: " + id);
        }
    }
}
