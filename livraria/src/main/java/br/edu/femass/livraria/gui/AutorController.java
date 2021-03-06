package br.edu.femass.livraria.gui;

import br.edu.femass.livraria.dao.AutorDao;
import br.edu.femass.livraria.model.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AutorController implements Initializable {
    private AutorDao autorDao = new AutorDao();

    @FXML
    private ListView<Autor> LstAutores;

    @FXML
    private Button BtnIncluir;

    @FXML
    private Button BtnExcluir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtSobrenome;


    private void limparTela() {
        TxtSobrenome.setText("");
        TxtNome.setText("");
    }
    private void habilitarInterface(Boolean incluir) {
        TxtNome.setDisable(!incluir);
        TxtSobrenome.setDisable(!incluir);
        BtnGravar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnExcluir.setDisable(incluir);
        BtnIncluir.setDisable(incluir);
        LstAutores.setDisable(incluir);
    }

    private void exibirAutor() {
        Autor autor = LstAutores.getSelectionModel().getSelectedItem();
        if (autor==null) return;
        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getSobrenome());

    }

    @FXML
    private void LstAutores_MouseClicked(MouseEvent evento) {
        exibirAutor();
    }

    @FXML
    private void LstAutores_KeyPressed(KeyEvent evento) {
        exibirAutor();
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
        Autor autor = LstAutores.getSelectionModel().getSelectedItem();

        if (autor==null) return;

        try {
            autorDao.excluir(autor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();

    }
    @FXML
    private void BtnGravar_Action(ActionEvent evento) {
        Autor autor = new Autor();
        autor.setSobrenome(TxtSobrenome.getText());
        autor.setNome(TxtNome.getText());
        if (TxtSobrenome.getText() == "" || TxtNome.getText() == ""){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error 403");
            errorAlert.setContentText("?? pro??bido gravar dados nulos!");
            errorAlert.showAndWait();
        }
        else {
            try {
                autorDao.gravar(autor);
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
        List<Autor> autores;
        try {
            autores = autorDao.listar();
        } catch (Exception e) {
            autores = new ArrayList<>();
        }
        ObservableList<Autor> autoresOb = FXCollections.observableArrayList(autores);
        LstAutores.setItems(autoresOb);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }
}
