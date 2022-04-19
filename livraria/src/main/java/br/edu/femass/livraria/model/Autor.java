package br.edu.femass.livraria.model;

import lombok.Data;

@Data
public class Autor {
    private String nome;
    private String sobrenome;

    public String toString(){
        return(nome +" " + sobrenome);
    }
}
