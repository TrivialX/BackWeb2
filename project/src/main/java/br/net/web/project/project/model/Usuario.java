package br.net.web.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Setter @Getter
    private int id;
    
    @Setter @Getter
    private String nome;
    
    @Setter @Getter
    private String email;
    
    @Setter @Getter
    private String senha;

    @Setter @Getter
    private String perfil;
}
