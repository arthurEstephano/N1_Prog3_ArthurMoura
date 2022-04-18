package br.edu.femass.livraria.gui;

import br.edu.femass.livraria.dao.AcademicoDao;
import br.edu.femass.livraria.dao.LivroDao;
import br.edu.femass.livraria.model.Academico;
import br.edu.femass.livraria.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MovimentacaoController implements Initializable {
    private final LivroDao livroDao = new LivroDao();
    private final AcademicoDao academicoDao = new AcademicoDao();

    @FXML
    private ListView<Livro> LstLivros;

    @FXML
    private ListView<Academico> LstAcademicos;

    @FXML
    private Button BtnAlugar;

    @FXML
    private Button BtnDevolver;

    @FXML
    private ChoiceBox ChBoxLivros;

    @FXML
    private ChoiceBox ChBoxAcademicos;

    @FXML
    private void BtnDevolver_Action(ActionEvent evento) {
        Livro livro = (Livro) ChBoxLivros.getSelectionModel().getSelectedItem();
        Academico academico = (Academico) ChBoxAcademicos.getSelectionModel().getSelectedItem();

        academico.Retornar_Livro(livro);
        atualizarLista();
    }
    @FXML
    private void BtnAlugar_Action(ActionEvent evento) {
        if(ChBoxLivros.getSelectionModel().getSelectedItem() =="" || ChBoxAcademicos.getSelectionModel().getSelectedItem() == ""){
            return;
        }
        Livro livro = (Livro) ChBoxLivros.getSelectionModel().getSelectedItem();
        Academico academico = (Academico) ChBoxAcademicos.getSelectionModel().getSelectedItem();


        academico.Alugar_Livro(livro);
        academicoDao.update();
        livroDao.update();
        atualizarLista();
    }

    @FXML

    private void atualizarLista() {
        List<Livro> livros;
        List<Academico> academicos;
        try {
            livros = livroDao.listar();
            academicos = academicoDao.listar();
        } catch (Exception e) {
            livros = new ArrayList<>();
            academicos = new ArrayList<>();
        }

        ObservableList<Livro> livrosOb = FXCollections.observableArrayList(livros);
        LstLivros.setItems(livrosOb);
        ChBoxLivros.setItems(livrosOb);

        ObservableList<Academico> academicosOb = FXCollections.observableArrayList(academicos);
        LstAcademicos.setItems(academicosOb);
        ChBoxAcademicos.setItems(academicosOb);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }
}
