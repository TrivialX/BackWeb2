package br.net.web.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class CategoriaEquipamento {
    @Setter @Getter
    private int idCategoria;
    
    @Setter @Getter
    private String nomeCategoria;
}
