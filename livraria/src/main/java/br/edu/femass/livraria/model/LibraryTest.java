package br.edu.femass.livraria.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    Autor autor = new Autor("Marcos", "Quieza");
    Livro livro = new Livro(1999, 1, GeneroLivro.Terror, "Teste incríveis",autor);
    Academico academico = new Academico("Carlos", "12345678910");
    }