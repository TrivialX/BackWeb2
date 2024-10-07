package br.net.web.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
    @Setter @Getter
    private int idPagamento;
    
    @Setter @Getter
    private Solicitacao solicitacao;
    
    @Setter @Getter
    private BigDecimal valor;
    
    @Setter @Getter
    private LocalDateTime dataPagamento;
}
