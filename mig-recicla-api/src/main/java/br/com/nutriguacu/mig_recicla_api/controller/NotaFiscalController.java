package br.com.nutriguacu.mig_recicla_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nutriguacu.mig_recicla_api.model.NotaFiscal;
import br.com.nutriguacu.mig_recicla_api.repository.NotaFiscalRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notas-fiscais")
@RequiredArgsConstructor
public class NotaFiscalController {

	private final NotaFiscalRepository notaFiscalRepository;

	@GetMapping
	public List<NotaFiscal> listar() {
		return notaFiscalRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody NotaFiscal notaFiscal) {
		if (notaFiscalRepository.existsByNumeroDaNota(notaFiscal.getNumeroDaNota())) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("{\"erro\": \"Esta Nota Fiscal já está cadastrada no sistema!\"}");
		}

		NotaFiscal salva = notaFiscalRepository.save(notaFiscal);
		return ResponseEntity.status(HttpStatus.CREATED).body(salva);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		if (!notaFiscalRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		notaFiscalRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}