package br.net.web.project.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
@Setter @Getter
private int id;
@Setter @Getter
private String nome;
@Setter @Getter
private int idade;
@Setter @Getter
private String dataNascimento;
@Setter @Getter
private String motorista;
}