package br.edu.femass.livraria.dao;

import br.edu.femass.livraria.model.Autor;

import java.util.ArrayList;
import java.util.List;

public class AutorDados {
    private List<Autor> autor = new ArrayList<Autor>();

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }
}
