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
/**
 * Controller class for managing client-related operations.
 * @author Karolis Jakas Stirbyte
 */
public class clientController {
    private MainController MainController;

    /**
     * Sets the main controller.
     *
     * @param MainController The main controller instance to set.
     */
    public void setMainController(MainController MainController) {
        this.MainController = MainController;
    }

    clientModel clientModel = new clientModel();

    /**
     * Retrieves information of all clients, for the tableView
     *
     * @return Information of the clients.
     */
    public String getClientsInfo(){
        return clientModel.getClients();
    }

    /**
     * Creates a form to consult a client by their ID.
     *
     * @param VBox Container where the form will be added.
     */
    public void consultFormClient(VBox VBox){
        VBox.getChildren().clear();
        Label consultClientIdLabel = new Label("ID cliente a consultar:");
        TextField consultClientIDField = new TextField();

        Button consultClientButton = new Button("Enviar");

        consultClientButton.setOnAction(e ->
                consultClient(consultClientIDField)
        );

        VBox.getChildren().addAll(consultClientIdLabel, consultClientIDField, consultClientButton);
        VBox.setMargin(consultClientButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Performs the action of consulting a client by their ID.
     *
     * @param consultClientIDField TextField containing the client ID to consult.
     */
    public void consultClient(TextField consultClientIDField) {
        String input = consultClientIDField.getText();

        if (isNumeric(input)) {
            String clientData = clientModel.consultClient(Integer.parseInt(input));
            if (!clientData.isEmpty()) {
                consultClientIDField.clear();
                clientInfo(clientData);
            } else {
                showAlert("Error", "No se ha encontrado al cliente");
            }
        } else {
            showAlert("Error", "El id debe ser un numero!");
        }
    }

    /**
     * Creates a form to add a new client.
     *
     * @param VBox Container where the form will be added.
     */
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

        Button addClientButton = new Button("Enviar");

        addClientButton.setOnAction(e -> {
            addClient(nameField, surname1Field, surname2Field, phoneField);
        });

        VBox.getChildren().addAll(nameLabel, nameField, surname1Label, surname1Field, surname2Label, surname2Field, phoneLabel, phoneField, addClientButton);
        VBox.setMargin(addClientButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Adds a new client based on the provided information.
     *
     * @param name  TextField containing the name of the client.
     * @param sur1  TextField containing the first surname of the client.
     * @param sur2  TextField containing the second surname of the client.
     * @param phone TextField containing the phone number of the client.
     */
    public void addClient(TextField name, TextField sur1, TextField sur2, TextField phone) {
        String nameInput = name.getText();
        String surname1Input = sur1.getText();
        String surname2Input = sur2.getText();
        String phoneInput = phone.getText();

        if (nameInput.length() >= 50 || nameInput.isEmpty() || isNumeric(nameInput)) {
            showAlert("Error", "El nombre debe tener entre 1 y 50 caracteres y deben tener al menos una letra!");
            return;
        }

        if ((surname1Input.length() >= 50 || surname1Input.isEmpty()) || (surname2Input.length() >= 50 || surname2Input.isEmpty()) || isNumeric(surname1Input) || isNumeric(surname2Input)) {
            showAlert("Error", "Los apellidos deben tener entre 1 y 50 caracteres y deben tener al menos una letra!");
            return;
        }

        if (!isNumeric(phoneInput)) {
            showAlert("Error", "El telefono debe ser un numero!");
            return;
        }

        if (phoneInput.length() != 9) {
            showAlert("Error", "El numero de telefono deben de ser 9 caracteres!");
            return;
        }

        name.clear();
        sur1.clear();
        sur2.clear();
        phone.clear();
        clientModel.insertClient(nameInput, surname1Input, surname2Input ,phoneInput);
        MainController.getInstance().refreshClientTable();
        showAlert("Cliente añadido!", "Cliente añadido con exito!");

    }

    /**
     * Creates a form to delete a client by their ID.
     *
     * @param VBox Container where the form will be added.
     */
    public void deleteClientForm(VBox VBox){
        VBox.getChildren().clear();
        Label deleteClientIdLabel = new Label("ID cliente a borrar:");
        TextField deleteClientIDField = new TextField();

        Button deleteClientButton = new Button("Enviar");

        deleteClientButton.setOnAction(e -> {
            deleteClient(deleteClientIDField);
        });

        VBox.getChildren().addAll(deleteClientIdLabel, deleteClientIDField, deleteClientButton);
        VBox.setMargin(deleteClientButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Deletes a client based on the provided ID.
     *
     * @param deleteClient TextField containing the ID of the client to delete.
     */
    public void deleteClient(TextField deleteClient){
        String input = deleteClient.getText();

        if (isNumeric(input)) {
            String clientData = clientModel.consultClient(Integer.parseInt(input));
            if (!clientData.isEmpty()) {
                deleteClient.clear();
                clientModel.deleteClient(Integer.parseInt(input));
                MainController.getInstance().refreshClientTable();
                MainController.getInstance().refreshBuyTable();
                showAlert("Cliente borrado!" , "El cliente con el id " + input + " ha sido borrado!");
            } else {
                showAlert("Error", "No se ha encontrado al cliente");
            }
        } else {
            showAlert("Error", "El id debe ser un numero!");
        }
    }

    /**
     * Creates a form to search and modify a client by their ID.
     *
     * @param VBox Container where the form will be added.
     */
    public void searchModifyClientForm(VBox VBox){
        VBox.getChildren().clear();
        Label modifyClientIdLabel = new Label("ID cliente a modificar:");
        TextField clientIDField = new TextField();

        Button searchModifyClientButton = new Button("Enviar");

        searchModifyClientButton.setOnAction(e -> {
            String input = clientIDField.getText();

            if (isNumeric(input)) {
                String clientData = clientModel.consultClient(Integer.parseInt(input));
                if (!clientData.isEmpty()) {
                    modifyClientForm(VBox, Integer.parseInt(input));
                } else {
                    showAlert("Error", "No se ha encontrado al cliente");
                }
            } else {
                showAlert("Error", "El id debe ser un numero!");
            }
        });

        VBox.getChildren().addAll(modifyClientIdLabel, clientIDField, searchModifyClientButton);
        VBox.setMargin(searchModifyClientButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Creates a form to modify a client based on the provided ID.
     *
     * @param VBox    Container where the form will be added.
     * @param inputID ID of the client to modify.
     */
    public void modifyClientForm(VBox VBox, int inputID){
        VBox.getChildren().clear();
        String[] clientData = clientModel.getClientInfo(inputID).split(":");

        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();
        nameField.setText(clientData[0]);

        Label surname1Label = new Label("Apellido1:");
        TextField surname1Field = new TextField();
        surname1Field.setText(clientData[1]);

        Label surname2Label = new Label("Apellido2:");
        TextField surname2Field = new TextField();
        surname2Field.setText(clientData[2]);

        Label phoneLabel = new Label("Telefono:");
        TextField phoneField = new TextField();
        phoneField.setText(clientData[3]);

        Button modifyClientButton = new Button("Enviar");

        modifyClientButton.setOnAction(e -> {
            modifyClient(inputID, nameField, surname1Field, surname2Field, phoneField, VBox);
        });

        VBox.getChildren().addAll(nameLabel, nameField, surname1Label, surname1Field, surname2Label, surname2Field, phoneLabel, phoneField, modifyClientButton);
        VBox.setMargin(modifyClientButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Modifies a client based on the provided information.
     *
     * @param ID    ID of the client to modify.
     * @param name  TextField containing the new name of the client.
     * @param sur1  TextField containing the new first surname of the client.
     * @param sur2  TextField containing the new second surname of the client.
     * @param phone TextField containing the new phone number of the client.
     * @param VBox  Container where the form is located.
     */
    public void modifyClient(int ID,TextField name, TextField sur1, TextField sur2, TextField phone, VBox VBox) {
        String nameInput = name.getText();
        String surname1Input = sur1.getText();
        String surname2Input = sur2.getText();
        String phoneInput = phone.getText();

        if (nameInput.length() >= 50 || nameInput.isEmpty() || isNumeric(nameInput)) {
            showAlert("Error", "El nombre debe tener entre 1 y 50 caracteres y deben tener al menos una letra!");
            return;
        }

        if ((surname1Input.length() >= 50 || surname1Input.isEmpty()) || (surname2Input.length() >= 50 || surname2Input.isEmpty()) || isNumeric(surname1Input) || isNumeric(surname2Input)) {
            showAlert("Error", "Los apellidos deben tener entre 1 y 50 caracteres y deben tener al menos una letra!");
            return;
        }

        if (!isNumeric(phoneInput)) {
            showAlert("Error", "El telefono debe ser un numero!");
            return;
        }

        if (phoneInput.length() != 9) {
            showAlert("Error", "El numero de telefono deben de ser 9 caracteres!");
            return;
        }

        name.clear();
        sur1.clear();
        sur2.clear();
        phone.clear();
        clientModel.modifyClient(ID,nameInput, surname1Input, surname2Input ,phoneInput);
        showAlert("Cliente modificado!" , "El cliente con el id " + ID + " ha sido modificado!");
        MainController.getInstance().refreshClientTable();
        VBox.getChildren().clear();

    }

    /**
     * Displays information about a client in a pop-up window.
     *
     * @param data Information about the client.
     */
    private void clientInfo(String data){
        TextArea clienteInfoTextArea = new TextArea();
        clienteInfoTextArea.setEditable(false);

        clienteInfoTextArea.setText(data);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(clienteInfoTextArea);

        Scene scene = new Scene(root, 600, 400);

        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Información del Producto");
        popUpStage.setScene(scene);

        popUpStage.show();
    }

    /**
     * Checks if a string is numeric.
     *
     * @param str String to check.
     * @return True if the string is numeric, false otherwise.
     */
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

    /**
     * Displays an alert with the given title and message.
     *
     * @param title   Title of the alert.
     * @param message Message to display in the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}