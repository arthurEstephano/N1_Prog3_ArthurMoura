package br.edu.femass.livraria.model;

import lombok.Data;

@Data
public class Livro {
    private Integer ano;
    private Integer edicao;
    private GeneroLivro genero;
    private Boolean emprestimo;
    private Boolean disponibolidade;
    private static Integer proximo_identificador = 1;
    private Integer identificador;
    private String nome;
    private Autor autor;


    public Livro() {
        identificador = proximo_identificador;
        proximo_identificador++;
    }

    @Override
    public String toString(){
        return(this.nome + " " + this.edicao + "edição, do ano " + this.ano +" e do gênero " + this.genero + ", é de autoria de: " + this.autor);
    }
    }

