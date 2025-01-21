package com.ux_project.conclusion.domain.endereco;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.salvarEndereco(endereco);
        return ResponseEntity.ok(novoEndereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> buscarTodos() {
        List<Endereco> enderecos = enderecoService.buscarTodos();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        return enderecoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco novoEndereco) {
        try {
            Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, novoEndereco);
            return ResponseEntity.ok(enderecoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Long id) {
        try {
            enderecoService.deletarEndereco(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
