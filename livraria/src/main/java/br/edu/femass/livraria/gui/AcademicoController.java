package br.edu.femass.livraria.gui;

import br.edu.femass.livraria.dao.AcademicoDao;
import br.edu.femass.livraria.model.Academico;
import br.edu.femass.livraria.model.Estado_Academico;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class AcademicoController implements Initializable {
    private AcademicoDao academicoDao = new AcademicoDao();

    @FXML
    private ListView<Academico> LstAcademicos;

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
    private TextField TxtCPF;

    @FXML
    private ComboBox<Estado_Academico> CboEstado_Academico;


    private void limparTela() {
        CboEstado_Academico.setValue(null);
        TxtCPF.setText("");
        TxtNome.setText("");
    }
    private void habilitarInterface(Boolean incluir) {
        TxtNome.setDisable(!incluir);
        TxtCPF.setDisable(!incluir);
        CboEstado_Academico.setDisable(!incluir);
        BtnGravar.setDisable(!incluir);
        BtnCancelar.setDisable(!incluir);
        BtnExcluir.setDisable(incluir);
        BtnIncluir.setDisable(incluir);
        LstAcademicos.setDisable(incluir);
    }

    private void exibirAcademico() {
        Academico academico = LstAcademicos.getSelectionModel().getSelectedItem();
        if (academico==null) return;
        TxtNome.setText(academico.getNome());
        TxtCPF.setText(academico.getCpf());
        CboEstado_Academico.setValue(academico.getEstado_academico());
    }

    @FXML
    private void LstAcademicos_MouseClicked(MouseEvent evento) {
        exibirAcademico();
    }

    @FXML
    private void LstAcademicos_KeyPressed(KeyEvent evento) {
        exibirAcademico();
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
        Academico academico = LstAcademicos.getSelectionModel().getSelectedItem();

        if (academico==null) return;

        try {
            academicoDao.excluir(academico);
        } catch (Exception e) {
            e.printStackTrace();
        }

        atualizarLista();

    }
    @FXML
    private void BtnGravar_Action(ActionEvent evento) {
        Academico academico = new Academico();
        academico.setCpf(TxtCPF.getText());
        academico.setNome(TxtNome.getText());
        academico.setEstado_academico(CboEstado_Academico.getValue());
        if (Objects.equals(TxtCPF.getText(), "") || TxtNome.getText() == "" || CboEstado_Academico.getValue() == null){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error 403");
            errorAlert.setContentText("É proíbido gravar dados nulos!");
            errorAlert.showAndWait();
        }
        else {
            try {
                academicoDao.gravar(academico);
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
        List<Academico> academicos;
        try {
            academicos = academicoDao.listar();
        } catch (Exception e) {
            academicos = new ArrayList<>();
        }
        ObservableList<Academico> academicosOb = FXCollections.observableArrayList(academicos);
        LstAcademicos.setItems(academicosOb);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }
}
