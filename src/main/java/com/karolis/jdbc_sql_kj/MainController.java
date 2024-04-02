package com.karolis.jdbc_sql_kj;

import Models.buyModel;
import Models.clientModel;
import Models.productModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    public VBox clientVBox;
    private int result = 0;
    buyModel buy = new buyModel();
    clientModel client = new clientModel();
    productModel product = new productModel();

    public void consultClient(){
        clientVBox.getChildren().clear();
        Label consultClientIdLabel = new Label("ID cliente a consultar:");
        TextField consultClientIDField = new TextField();

        // Crear un botón
        Button consultClientButton = new Button("Enviar");

        // Definir el evento onAction del botón
        consultClientButton.setOnAction(e -> {
            System.out.println("Button clicked!"); // Aquí puedes agregar la lógica que desees ejecutar cuando se haga clic en el botón
        });

        clientVBox.getChildren().addAll(consultClientIdLabel, consultClientIDField, consultClientButton);
        VBox.setMargin(consultClientButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void addClient(){
        clientVBox.getChildren().clear();
        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();

        Label surname1Label = new Label("Apellido1:");
        TextField surname1Field = new TextField();

        Label surname2Label = new Label("Apellido2:");
        TextField surname2Field = new TextField();

        Label phoneLabel = new Label("Telefono:");
        TextField phoneField = new TextField();

        // Crear un botón
        Button addClientButton = new Button("Enviar");

        // Definir el evento onAction del botón
        addClientButton.setOnAction(e -> {
            System.out.println("Button clicked!"); // Aquí puedes agregar la lógica que desees ejecutar cuando se haga clic en el botón
        });

        clientVBox.getChildren().addAll(nameLabel, nameField, surname1Label, surname1Field, surname2Label, surname2Field, phoneLabel, phoneField, addClientButton);
        VBox.setMargin(addClientButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void deleteClient(){
        clientVBox.getChildren().clear();
        Label deleteClientIdLabel = new Label("ID cliente a borrar:");
        TextField deleteClientIDField = new TextField();

        // Crear un botón
        Button deleteClientButton = new Button("Enviar");

        // Definir el evento onAction del botón
        deleteClientButton.setOnAction(e -> {
            System.out.println("Button clicked!"); // Aquí puedes agregar la lógica que desees ejecutar cuando se haga clic en el botón
        });

        clientVBox.getChildren().addAll(deleteClientIdLabel, deleteClientIDField, deleteClientButton);
        VBox.setMargin(deleteClientButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void modifyClient(){
        clientVBox.getChildren().clear();
        Label modifyClientIdLabel = new Label("ID cliente a modificar:");
        TextField clientIDField = new TextField();

        // Crear un botón
        Button deleteClientButton = new Button("Enviar");

        // Definir el evento onAction del botón
        deleteClientButton.setOnAction(e -> {
            System.out.println("Button clicked!"); // Aquí puedes agregar la lógica que desees ejecutar cuando se haga clic en el botón
        });

        clientVBox.getChildren().addAll(modifyClientIdLabel, clientIDField, deleteClientButton);
        VBox.setMargin(deleteClientButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void createBuyTable(){
        result = buy.createTable();
        if(result == -1) System.out.println("Error BBDD!");
        else System.out.println("Tabla creada!");
    }
}