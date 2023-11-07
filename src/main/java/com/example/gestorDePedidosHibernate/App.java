package com.example.gestorDePedidosHibernate;

import com.example.gestorDePedidosHibernate.domain.usuario.UsuarioDAO;
import jakarta.persistence.GeneratedValue;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

public class App extends Application {
    @Getter
    @Setter
    private static Stage myStage;
    @Override
    public void start(Stage stage) throws IOException {
        myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("controllers/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void loadFXML(String fxml, String titulo){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("controllers/"+fxml));
            Scene scene = new Scene(fxmlLoader.load());
            myStage.setTitle(titulo);
            myStage.setScene(scene);
            myStage.show();
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo "+fxml);
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {
        launch();
    }
}