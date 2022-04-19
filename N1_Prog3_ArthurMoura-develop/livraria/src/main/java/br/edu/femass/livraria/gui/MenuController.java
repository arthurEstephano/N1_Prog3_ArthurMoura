package br.edu.femass.livraria.gui;

import br.edu.femass.livraria.LibraryApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button BtnAcademicos;

    @FXML
    private Button BtnLivros;

    @FXML
    private Button BtnAutores;

    @FXML
    private Button BtnMovimentacao;

    @FXML
    private Button BtnCopiar;

    @FXML
    private Button BtnBiblio;

    @FXML
    private void BtnAcademicos_Action(ActionEvent evento) throws IOException {
        Stage stageAcademico = new Stage();
        FXMLLoader fxmlLoaderAcademico = new FXMLLoader(LibraryApplication.class.getResource("academico-view.fxml"));
        Scene sceneAcademico = new Scene(fxmlLoaderAcademico.load(), 575, 356);
        stageAcademico.setTitle("Acadêmicos!");
        stageAcademico.setScene(sceneAcademico);
        stageAcademico.show();
    }

    @FXML
    private void BtnLivros_Action(ActionEvent evento) throws IOException {
        Stage stageLivro = new Stage();
        FXMLLoader fxmlLoaderLivro = new FXMLLoader(LibraryApplication.class.getResource("livro-view.fxml"));
        Scene sceneLivro = new Scene(fxmlLoaderLivro.load(), 575, 356);
        stageLivro.setTitle("Livros!");
        stageLivro.setScene(sceneLivro);
        stageLivro.show();
    }

    @FXML
    private void BtnAutores_Action(ActionEvent evento) throws IOException {
        Stage stageAutor = new Stage();
        FXMLLoader fxmlLoaderAutor = new FXMLLoader(LibraryApplication.class.getResource("autor-view.fxml"));
        Scene sceneAutor = new Scene(fxmlLoaderAutor.load(), 575, 356);
        stageAutor.setTitle("Autores!");
        stageAutor.setScene(sceneAutor);
        stageAutor.show();
    }


    @FXML
    private void BtnMovimentacao_Action(ActionEvent evento) throws IOException {
        Stage stageMovimentacao = new Stage();
        FXMLLoader fxmlLoaderMovimentacao = new FXMLLoader(LibraryApplication.class.getResource("movimentacao-view.fxml"));
        Scene sceneMovimentacao = new Scene(fxmlLoaderMovimentacao.load(), 575, 356);
        stageMovimentacao.setTitle("Movimentação de livros!");
        stageMovimentacao.setScene(sceneMovimentacao);
        stageMovimentacao.show();
    }

    @FXML
    private void BtnCopiar_Action(ActionEvent evento) throws IOException {
        Stage stageCopia = new Stage();
        FXMLLoader fxmlLoaderCopia = new FXMLLoader(LibraryApplication.class.getResource("copiar-livro-view.fxml"));
        Scene sceneCopia = new Scene(fxmlLoaderCopia.load(), 575, 356);
        stageCopia.setTitle("Copiar livros!");
        stageCopia.setScene(sceneCopia);
        stageCopia.show();
    }

    @FXML
    private void BtnBiblio_Action(ActionEvent evento) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setHeaderText("Informativo.");
        infoAlert.setContentText("Está opção está em desenvolvimento, em breve estará disponível para uso.");
        infoAlert.showAndWait();
    }

}
