package br.edu.femass.livraria.model;

public enum Estado_Academico {
    Professor("Professor"),
    Aluno("Livro de Comédia");

    private String nome;

    Estado_Academico(String nome) {
        this.nome = nome;
    }
}
