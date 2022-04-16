package br.edu.femass.livraria.gui;

import br.edu.femass.livraria.dao.AutorDao;
import br.edu.femass.livraria.dao.LivroDao;
import br.edu.femass.livraria.model.Autor;
import br.edu.femass.livraria.model.GeneroLivro;
import br.edu.femass.livraria.model.Livro;
import javafx.beans.binding.Bindings;
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

public class LivroController implements Initializable {

    private LivroDao livroDao = new LivroDao();
    private AutorDao autorDao = new AutorDao();

    @FXML
    private ListView<Livro> LstLivros;

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtIdentificador;

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtEdicao;

    @FXML
    private TextField TxtAno;;

    @FXML
    private ComboBox<GeneroLivro> CboGenero;

    @FXML
    private ComboBox<Autor> CboAutor;

    private void limparTela() {
        TxtIdentificador.setText("");
        TxtAno.setText("");
        TxtEdicao.setText("");
        TxtNome.setText("");
        CboGenero.setValue(null);
        CboAutor.setValue(null);
    }
    private void habilitarInterface(Boolean incluir) {
        TxtAno.setDisable(!incluir);
        TxtEdicao.setDisable(!incluir);
        TxtNome.setDisable(!incluir);
        CboGenero.setDisable(!incluir);
        BtnGravar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnExcluir.setDisable(incluir);
        BtnIncluir.setDisable(incluir);
        CboAutor.setDisable(!incluir);

        LstLivros.setDisable(incluir);
    }

    private void exibirLivro() {
        Livro livro = LstLivros.getSelectionModel().getSelectedItem();
        if (livro==null) return;
        TxtNome.setText(livro.getNome());
        TxtAno.setText(livro.getAno().toString());
        TxtEdicao.setText(livro.getEdicao().toString());
        TxtIdentificador.setText(livro.getIdentificador().toString());
        CboGenero.setValue(livro.getGenero());
        CboAutor.setValue(livro.getAutor());

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
    private void BtnIncluir_Action(ActionEvent evento) {
        atualizarLista();
        habilitarInterface(true);
        limparTela();
        TxtNome.requestFocus();
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
    private void BtnGravar_Action(ActionEvent evento) {
        Livro livro = new Livro();
        livro.setAno(Integer.parseInt(TxtAno.getText()));
        livro.setIdentificador(livro.getIdentificador());
        livro.setGenero(CboGenero.getValue());
        livro.setAutor(CboAutor.getValue());
        livro.setNome(TxtNome.getText());
        livro.setEdicao(Integer.parseInt(TxtEdicao.getText()));
        if (CboGenero.getValue() == null || CboAutor.getValue() == null || TxtNome.getText() == ""){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error 403");
            errorAlert.setContentText("É proíbido gravar dados nulos!");
            errorAlert.showAndWait();
        }
        else {
            try {
                livroDao.gravar(livro);
            } catch (Exception e) {
                e.printStackTrace();
            }
            atualizarLista();
            habilitarInterface(false);
        }
    }

    @FXML
    private void BtnCancelar_Action(ActionEvent evento) {
        habilitarInterface(false);
    }


    private void atualizarLista() {
        List<Livro> livros;
        List<Autor> autores;
        try {
            livros = livroDao.listar();
            autores = autorDao.listar();
        } catch (Exception e) {
            livros = new ArrayList<>();
            autores = new ArrayList<>();
        }

        ObservableList<Livro> livrosOb = FXCollections.observableArrayList(livros);
        ObservableList<Autor> autorOb = FXCollections.observableArrayList(autores);
        LstLivros.setItems(livrosOb);
        CboAutor.setItems(autorOb);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<GeneroLivro> generos = FXCollections.observableArrayList(GeneroLivro.values());
        CboGenero.setItems(generos);
        atualizarLista();
    }
}