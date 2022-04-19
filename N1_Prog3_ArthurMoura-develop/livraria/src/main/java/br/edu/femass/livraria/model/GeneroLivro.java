package br.edu.femass.livraria.model;

public enum GeneroLivro {

    Acao("Livro de Ação"),
    Comedia("Livro de Comédia"),
    Terror("Livro de Terror"),
    Suspense("Livro de Suspense"),
    Drama("Livro de Drama"),
    Ficcao("Livro de Ficção Científica");

    private String nome;

    GeneroLivro(String nome) {
        this.nome = nome;
    }
}
