package br.edu.femass.livraria.dao;

import br.edu.femass.livraria.model.Academico;

import java.util.ArrayList;
import java.util.List;

public class AcademicoDados {
    private List<Academico> academico = new ArrayList<>();

    public List<Academico> getAcademico() {
        return academico;
    }

    public void setAcademico(List<Academico> academico) {
        this.academico = academico;
    }
}
