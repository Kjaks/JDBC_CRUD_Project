package com.karolis.jdbc_sql_kj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The CeliaShop class represents the main entry point of the Celia Shop application.
 * This application is a simple shop interface.
 * @author Karolis Jakas Stirbyte
 */
public class CeliaShop extends Application {

    /**
     * Starts the application by loading the main view from the FXML file and displaying it on the stage.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CeliaShop.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("CELIA SHOP : KAROLIS JAKAS STIRBYTE 2024 ©");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method of the CeliaShop application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        launch();
    }
}
