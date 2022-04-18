package br.edu.femass.livraria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderMenu = new FXMLLoader(LibraryApplication.class.getResource("menu-view.fxml"));
        Scene sceneMenu = new Scene(fxmlLoaderMenu.load(), 483, 200);
        stage.setTitle("Menu interativo.");
        stage.setScene(sceneMenu);
        stage.show();

        //TODO: Tirar dúvida sobre seleção de paineis múltiplos
    }



    public static void main(String[] args) {
        launch();
    }
}