package br.net.web.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends Usuario {
    @Setter @Getter
    private String dataNascimento;
}
