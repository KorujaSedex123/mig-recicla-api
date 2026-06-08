package br.com.nutriguacu.mig_recicla_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriguacu.mig_recicla_api.model.EmbalagemRetorno;
import br.com.nutriguacu.mig_recicla_api.model.StatusDevolucao;
import br.com.nutriguacu.mig_recicla_api.repository.EmbalagemRetornoRepository;
import br.com.nutriguacu.mig_recicla_api.service.EmbalagemRetornoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/embalagens-retorno")
@RequiredArgsConstructor
public class EmbalagemRetornoController {

    private final EmbalagemRetornoRepository repository;
    
    private final EmbalagemRetornoService service;

    @GetMapping
    public List<EmbalagemRetorno> listar() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmbalagemRetorno adicionar(@RequestBody EmbalagemRetorno embalagem) {
        if (embalagem.getStatus() == null) {
            embalagem.setStatus(StatusDevolucao.PENDENTE);
        }
        return repository.save(embalagem);
    }

    @PatchMapping("/{id}/registrar-retorno")
    public ResponseEntity<EmbalagemRetorno> registrarRetorno(
            @PathVariable Long id, 
            @RequestParam Integer quantidade,
            @RequestParam Long funcionarioId) { 
        try {
            EmbalagemRetorno embalagemAtualizada = service.registrarRetorno(id, quantidade, funcionarioId);
            return ResponseEntity.ok(embalagemAtualizada);
        } catch (RuntimeException e) {
           
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}