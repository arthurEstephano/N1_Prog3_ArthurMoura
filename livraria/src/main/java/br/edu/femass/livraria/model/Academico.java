package br.edu.femass.livraria.model;

import lombok.Data;
import java.util.*;

@Data
public abstract class Academico {
    private String nome;
    private String cpf;
    private String matricula;
    private Boolean livros_atrasados;
    private List<Livro> livro = new ArrayList<>();

    public Academico(String nome, String cpf, String matricula, Boolean livros_atrasados){
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.livros_atrasados = livros_atrasados;
    }

    public void Alugar_Livro(Livro livro){};
    public void Retornar_Livro(Livro livro){};
}
