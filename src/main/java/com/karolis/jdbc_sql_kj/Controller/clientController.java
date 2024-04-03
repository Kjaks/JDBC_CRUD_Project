package com.karolis.jdbc_sql_kj.Controller;

import Models.clientModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class clientController {
    clientModel clientModel = new clientModel();

    public String getClientsInfo(){
        return clientModel.getClients();
    }
    public void consultFormClient(VBox VBox){
        VBox.getChildren().clear();
        Label consultClientIdLabel = new Label("ID cliente a consultar:");
        TextField consultClientIDField = new TextField();

        // Crear un botón
        Button consultClientButton = new Button("Enviar");

        // Definir el evento onAction del botón
        consultClientButton.setOnAction(e -> {
            String input = consultClientIDField.getText();

            if (isNumeric(input) == false) {
                showAlert("Error", "El texto ingresado no es un número.");
            } else {
                String clientData = clientModel.consultClient(Integer.parseInt(input));
                if(clientData == ""){
                    showAlert("Error", "No se ha encontrado al cliente");
                } else{
                    clientInfo(clientData);
                }
            }
        });

        VBox.getChildren().addAll(consultClientIdLabel, consultClientIDField, consultClientButton);
        VBox.setMargin(consultClientButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void addClientForm(VBox VBox){
        VBox.getChildren().clear();
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

        VBox.getChildren().addAll(nameLabel, nameField, surname1Label, surname1Field, surname2Label, surname2Field, phoneLabel, phoneField, addClientButton);
        VBox.setMargin(addClientButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void deleteClientForm(VBox VBox){
        VBox.getChildren().clear();
        Label deleteClientIdLabel = new Label("ID cliente a borrar:");
        TextField deleteClientIDField = new TextField();

        // Crear un botón
        Button deleteClientButton = new Button("Enviar");

        // Definir el evento onAction del botón
        deleteClientButton.setOnAction(e -> {
            System.out.println("Button clicked!"); // Aquí puedes agregar la lógica que desees ejecutar cuando se haga clic en el botón
        });

        VBox.getChildren().addAll(deleteClientIdLabel, deleteClientIDField, deleteClientButton);
        VBox.setMargin(deleteClientButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void modifyClient(VBox VBox){
        VBox.getChildren().clear();
        Label modifyClientIdLabel = new Label("ID cliente a modificar:");
        TextField clientIDField = new TextField();

        // Crear un botón
        Button deleteClientButton = new Button("Enviar");

        // Definir el evento onAction del botón
        deleteClientButton.setOnAction(e -> {
            System.out.println("Button clicked!"); // Aquí puedes agregar la lógica que desees ejecutar cuando se haga clic en el botón
        });

        VBox.getChildren().addAll(modifyClientIdLabel, clientIDField, deleteClientButton);
        VBox.setMargin(deleteClientButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    private void clientInfo(String data){
        // Crear un TextArea para mostrar la información del cliente
        TextArea clienteInfoTextArea = new TextArea();
        clienteInfoTextArea.setEditable(false);

        // Configurar el TextArea con la información del cliente
        clienteInfoTextArea.setText(data);

        // Crear un diseño para la ventana emergente
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(clienteInfoTextArea);

        // Configurar la escena
        Scene scene = new Scene(root, 600, 400);

        // Configurar el nuevo Stage como modal
        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Información del Cliente");
        popUpStage.setScene(scene);

        // Mostrar el nuevo Stage
        popUpStage.show();
    }

    private boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
