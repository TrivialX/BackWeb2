package br.net.web.project.project.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class Solicitacao {
    @Setter @Getter
    private int idSolicitacao;
    
    @Setter @Getter
    private Cliente cliente;
    
    @Setter @Getter
    private Funcionario funcionario;
    
    @Setter @Getter
    private CategoriaEquipamento categoriaEquipamento;
    
    @Setter @Getter
    private String descricaoEquipamento;
    
    @Setter @Getter
    private String descricaoDefeito;
    
    @Setter @Getter
    private LocalDateTime dataHora;
    
    @Setter @Getter
    private String estado;
    
    @Setter @Getter
    private BigDecimal valorOrcamento;
    
    @Setter @Getter
    private LocalDateTime dataPagamento;
}
