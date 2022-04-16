package br.edu.femass.livraria.gui;

import br.edu.femass.livraria.dao.LivroDao;
import br.edu.femass.livraria.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CopiarLivroController implements Initializable {
    private LivroDao livroDao = new LivroDao();

    @FXML
    private ListView<Livro> LstLivros;

    @FXML
    private Button BtnAdicionar;

    @FXML
    private Button BtnExcluir;

    private void exibirLivro() {
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        if (livro==null) return;
    }

    @FXML
    private void LstLivros_MouseClicked(MouseEvent evento) {
        exibirLivro();
    }

    @FXML
    private void LstLivros_KeyPressed(KeyEvent evento) {
        exibirLivro();
    }

    @FXML
    private void BtnExcluir_Action(ActionEvent evento) {
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();

        if (livro==null) return;

        try {
            livroDao.excluir(livro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();

    }
    @FXML
    private void BtnAdicionar_Action(ActionEvent evento) {
        Livro livro = new Livro();
        livro.setNome(LstLivros.getSelectionModel().getSelectedItem().getNome());
        livro.setEdicao(LstLivros.getSelectionModel().getSelectedItem().getEdicao());
        livro.setAno(LstLivros.getSelectionModel().getSelectedItem().getAno());
        livro.setGenero(LstLivros.getSelectionModel().getSelectedItem().getGenero());
        livro.setAutor(LstLivros.getSelectionModel().getSelectedItem().getAutor());
        livro.setIdentificador(livro.getIdentificador() + 1 );
        livro.setDisponibolidade(true);

        try {
            livroDao.gravar(livro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarLista();
    }

    @FXML

    private void atualizarLista() {
        List<Livro> livros;
        try {
            livros = livroDao.listar();
        } catch (Exception e) {
            livros = new ArrayList<>();
        }

        ObservableList<Livro> livrosOb = FXCollections.observableArrayList(livros);
        LstLivros.setItems(livrosOb);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }
}
