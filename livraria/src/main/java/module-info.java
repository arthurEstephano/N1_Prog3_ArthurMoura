module br.edu.femass.livraria {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires xstream;
    requires org.junit.jupiter.api;


    opens br.edu.femass.livraria to javafx.fxml;
    exports br.edu.femass.livraria;
    exports br.edu.femass.livraria.gui;
    opens br.edu.femass.livraria.gui to javafx.fxml;
    opens br.edu.femass.livraria.model to xstream;
}