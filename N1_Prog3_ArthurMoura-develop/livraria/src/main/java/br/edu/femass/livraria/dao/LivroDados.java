package br.edu.femass.livraria.dao;

import br.edu.femass.livraria.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroDados {
    private List<Livro> livros = new ArrayList<Livro>();

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
