package br.net.web.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class HistoricoSolicitacao {
    @Setter @Getter
    private int idHistorico;
    
    @Setter @Getter
    private Solicitacao solicitacao;
    
    @Setter @Getter
    private Funcionario funcionario;
    
    @Setter @Getter
    private String estadoAnterior;
    
    @Setter @Getter
    private String estadoAtual;
    
    @Setter @Getter
    private LocalDateTime dataHora;
}
