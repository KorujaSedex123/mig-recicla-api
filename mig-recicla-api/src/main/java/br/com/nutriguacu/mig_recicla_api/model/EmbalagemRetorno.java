package br.com.nutriguacu.mig_recicla_api.model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "embalagens_retorno")
public class EmbalagemRetorno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidadeBags;
    
    @Column(name = "quantidade_retornada")
    private Integer quantidadeRetornada;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusDevolucao status;

    @Column(nullable = false)
    private LocalDate prazoLimite;

    private LocalDate dataRecebimento;

    // A qual NF essas embalagens pertencem?
    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id", nullable = false)
    private NotaFiscal notaFiscal;

    // Qual funcionário (ex: equipe de suporte/vendas) recolheu?
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionarioRecebedor;
}