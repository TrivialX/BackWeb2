package br.net.web.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Setter @Getter
    private String cep;

    @Setter @Getter
    private String logradouro;

    @Setter @Getter
    private String bairro;

    @Setter @Getter
    private String localidade; // Cidade

    @Setter @Getter
    private String uf; // Estado

    @Setter @Getter
    private String numero;

    @Setter @Getter
    private String complemento;
}
