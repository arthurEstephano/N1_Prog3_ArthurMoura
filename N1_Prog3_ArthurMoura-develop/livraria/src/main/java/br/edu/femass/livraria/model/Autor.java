package br.edu.femass.livraria.model;

import lombok.Data;

@Data
public class Autor {
    private String nome;
    private String sobrenome;

    public Autor(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Autor(){};

    public String toString(){
        return(nome +" " + sobrenome);
    }
}
