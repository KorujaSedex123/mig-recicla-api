package br.com.nutriguacu.mig_recicla_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nutriguacu.mig_recicla_api.model.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {
	boolean existsByNumeroDaNota(String numeroDaNota);
}