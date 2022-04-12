package br.edu.femass.livraria.dao;

import java.util.List;

public interface Dao<T> {
    public void gravar(T objeto) throws Exception;
    public List<T> listar() throws Exception;
    public void excluir(T objeto) throws Exception;

}
