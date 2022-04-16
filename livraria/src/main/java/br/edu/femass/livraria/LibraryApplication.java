package br.edu.femass.livraria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderLivro = new FXMLLoader(LibraryApplication.class.getResource("livro-view.fxml"));
        Scene sceneLivro = new Scene(fxmlLoaderLivro.load(), 575, 356);
        stage.setTitle("Livros!");
        stage.setScene(sceneLivro);
        stage.show();

        Stage stageAutor = new Stage();
        FXMLLoader fxmlLoaderAutor = new FXMLLoader(LibraryApplication.class.getResource("autor-view.fxml"));
        Scene sceneAutor = new Scene(fxmlLoaderAutor.load(), 575, 356);
        stageAutor.setTitle("Autores!");
        stageAutor.setScene(sceneAutor);
        stageAutor.show();

        Stage stageAcademico = new Stage();
        FXMLLoader fxmlLoaderAcademico = new FXMLLoader(LibraryApplication.class.getResource("academico-view.fxml"));
        Scene sceneAcademico = new Scene(fxmlLoaderAcademico.load(), 575, 356);
        stageAcademico.setTitle("Acadêmicos!");
        stageAcademico.setScene(sceneAcademico);
        stageAcademico.show();

        Stage stageCopia = new Stage();
        FXMLLoader fxmlLoaderCopia = new FXMLLoader(LibraryApplication.class.getResource("copiar-livro-view.fxml"));
        Scene sceneCopia = new Scene(fxmlLoaderCopia.load(), 575, 356);
        stageCopia.setTitle("Copiar livros!");
        stageCopia.setScene(sceneCopia);
        stageCopia.show();
    }

    public static void main(String[] args) {
        launch();
    }
}