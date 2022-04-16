package br.edu.femass.livraria.model;

import lombok.Data;
import java.util.*;

@Data
public class Academico {
    private String nome;
    private String cpf;
    private static Integer proxima_matricula = 1;
    private Estado_Academico estado_academico;
    private Integer matricula;
    private Boolean livros_atrasados = false;
    private List<Livro> livros = new ArrayList<>();

    public Academico() {
        this.matricula = proxima_matricula;
        proxima_matricula++;
    }

    public void Alugar_Livro(Livro livro){};
    public void Retornar_Livro(Livro livro){};
}
