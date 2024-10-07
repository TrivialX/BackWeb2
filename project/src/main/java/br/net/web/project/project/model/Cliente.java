package br.net.web.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Usuario {
    @Setter @Getter
    private String cpf;
    
    @Setter @Getter
    private String telefone;
    
    @Setter @Getter
    private String cep;
    
    @Setter @Getter
    private String endereco;
    
    @Setter @Getter
    private String numero;
    
    @Setter @Getter
    private String complemento;
    
    @Setter @Getter
    private String bairro;
    
    @Setter @Getter
    private String cidade;
    
    @Setter @Getter
    private String estado;
}
