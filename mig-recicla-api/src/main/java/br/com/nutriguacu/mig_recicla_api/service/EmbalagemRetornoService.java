package br.com.nutriguacu.mig_recicla_api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.nutriguacu.mig_recicla_api.model.EmbalagemRetorno;
import br.com.nutriguacu.mig_recicla_api.model.Funcionario;
import br.com.nutriguacu.mig_recicla_api.model.StatusDevolucao;
import br.com.nutriguacu.mig_recicla_api.repository.EmbalagemRetornoRepository;
import br.com.nutriguacu.mig_recicla_api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmbalagemRetornoService {

    private final EmbalagemRetornoRepository repository;
    private final FuncionarioRepository funcionarioRepository; 

    public EmbalagemRetorno registrarRetorno(Long idRegistro, Integer quantidadeQueVoltou, Long motoristaId) {
        
        // 1. Busca a embalagem
        EmbalagemRetorno embalagem = repository.findById(idRegistro)
                .orElseThrow(() -> new RuntimeException("Registro de embalagem não encontrado!"));

        // 2. Busca o funcionário/motorista que trouxe as bags
        Funcionario motorista = funcionarioRepository.findById(motoristaId)
                .orElseThrow(() -> new RuntimeException("Motorista/Funcionário não encontrado!"));

        // 3. Atualiza os dados
        embalagem.setQuantidadeBags(quantidadeQueVoltou);
        embalagem.setStatus(StatusDevolucao.RECEBIDO_NUTRIGUACU);
        embalagem.setDataRecebimento(LocalDate.now());
        
        // 4. Associa o motorista à devolução!
        embalagem.setFuncionarioRecebedor(motorista);

        return repository.save(embalagem);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void verificarPrazosVencidos() {
        List<EmbalagemRetorno> pendentes = repository.findByStatus(StatusDevolucao.PENDENTE);
        LocalDate hoje = LocalDate.now();

        for (EmbalagemRetorno embalagem : pendentes) {
            if (embalagem.getPrazoLimite().isBefore(hoje)) {
                embalagem.setStatus(StatusDevolucao.ATRASADO);
                repository.save(embalagem);
            }
        }
        System.out.println("Rotina automática: Verificação de bags atrasadas concluída.");
    }
}