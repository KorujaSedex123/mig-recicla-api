package br.com.nutriguacu.mig_recicla_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nutriguacu.mig_recicla_api.model.EmbalagemRetorno;
import br.com.nutriguacu.mig_recicla_api.model.StatusDevolucao;

@Repository
public interface EmbalagemRetornoRepository extends JpaRepository<EmbalagemRetorno, Long> {
    
    List<EmbalagemRetorno> findByStatus(StatusDevolucao status);
}