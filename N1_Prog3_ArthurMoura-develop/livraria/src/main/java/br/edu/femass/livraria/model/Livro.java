package br.edu.femass.livraria.model;

import lombok.Data;

import java.time.LocalDate;
@Data
public class Livro {
    private Integer ano;
    private Integer edicao;
    private GeneroLivro genero;
    private Boolean emprestimo = false;
    private Boolean disponibolidade = false;
    private static Integer proximo_identificador = 1;
    private Integer identificador;
    private String nome;
    private Autor autor;
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;
    private LocalDate data_prev_delovucao;

    public Livro() {
        identificador = proximo_identificador;
        proximo_identificador++;
    }

    public Livro(Integer ano, Integer edicao, GeneroLivro genero, String nome, Autor autor) {
        this.ano = ano;
        this.edicao = edicao;
        this.genero = genero;
        this.nome = nome;
        this.autor = autor;
    }

    @Override
    public String toString(){
        return(this.nome + " " + this.edicao + " edição, do ano " + this.ano +" e do gênero " + this.genero + ", é de autoria de: " + this.autor);
    }
    }

