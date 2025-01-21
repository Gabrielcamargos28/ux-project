package com.ux_project.conclusion.domain.doacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {

    private final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    @PostMapping
    public ResponseEntity<Doacao> criarDoacao(@RequestBody Doacao doacao) {
        Doacao novaDoacao = doacaoService.salvarDoacao(doacao);
        return ResponseEntity.ok(novaDoacao);
    }

    @GetMapping
    public ResponseEntity<List<Doacao>> buscarTodas() {
        List<Doacao> doacoes = doacaoService.buscarTodas();
        return ResponseEntity.ok(doacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doacao> buscarPorId(@PathVariable Long id) {
        return doacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doacao> atualizarDoacao(@PathVariable Long id, @RequestBody Doacao novaDoacao) {
        try {
            Doacao doacaoAtualizada = doacaoService.atualizarDoacao(id, novaDoacao);
            return ResponseEntity.ok(doacaoAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDoacao(@PathVariable Long id) {
        try {
            doacaoService.deletarDoacao(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
