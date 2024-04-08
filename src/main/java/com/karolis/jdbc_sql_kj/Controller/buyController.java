package com.karolis.jdbc_sql_kj.Controller;

import Models.buyModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller class for managing buy-related operations.
 * @author Karolis Jakas Stirbyte
 */
public class buyController {
    buyModel buyModel = new buyModel();
    /**
     * Creates the buy table. This method is requested by the teacher.
     */
    public void createBuyTable(){
        int result = 0;

        result = buyModel.createTable();

        if(result == -1) showAlert("Error", "Error al conectarse a la base de datos!");
        else showAlert("Exito", "Tabla creada con exito!");
    }

    /**
     * Retrieves information about all buys, for the tableView
     *
     * @return Information about all buys.
     */
    public String getBuyInfo() {
        return buyModel.getBuys();
    }

    /**
     * Creates a form to consult a buy by its ID.
     *
     * @param VBox Container where the form will be added.
     */
    public void consultFormBuy(VBox VBox){
        VBox.getChildren().clear();
        Label consultBuyIdLabel = new Label("ID a consultar:");
        TextField consultBuyIDField = new TextField();

        Button consultBuyButton = new Button("Enviar");

        consultBuyButton.setOnAction(e ->
                consultBuy(consultBuyIDField)
        );

        VBox.getChildren().addAll(consultBuyIdLabel, consultBuyIDField, consultBuyButton);
        VBox.setMargin(consultBuyButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Performs the action of consulting a buy by its ID.
     *
     * @param consultBuyIDField TextField containing the buy ID to consult.
     */
    public void consultBuy(TextField consultBuyIDField) {
        String input = consultBuyIDField.getText();

        if (isNumeric(input)) {
            String BuyData = buyModel.getBuyInfo(Integer.parseInt(input));
            if (!BuyData.isEmpty()) {
                consultBuyIDField.clear();
                BuyInfo(BuyData);
            } else {
                showAlert("Error", "No se ha encontrado la venta!");
            }
        } else {
            showAlert("Error", "El id debe ser un numero!");
        }
    }

    /**
     * Creates a form to add a new buy.
     *
     * @param VBox Container where the form will be added.
     */
    public void addBuyForm(VBox VBox){
        VBox.getChildren().clear();
        Label clientLabel = new Label("ID_cliente");
        TextField clientField = new TextField();

        Label productLabel = new Label("ID_producto:");
        TextField productField = new TextField();

        Label dateLabel = new Label("Fecha:");
        TextField dateField = new TextField();

        Button addBuyButton = new Button("Enviar");

        addBuyButton.setOnAction(e -> {
            addBuy(clientField, productField, dateField);
        });

        VBox.getChildren().addAll(clientLabel, clientField, productLabel, productField, dateLabel, dateField, addBuyButton);
        VBox.setMargin(addBuyButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Adds a new buy based on the provided information.
     *
     * @param id_client TextField containing the client ID for the buy.
     * @param id_product TextField containing the product ID for the buy.
     * @param date       TextField containing the date of the buy.
     */
    public void addBuy(TextField id_client, TextField id_product, TextField date) {
        String clientInput = id_client.getText();
        String productInput = id_product.getText();
        String dateInput = date.getText();

        if (!isNumeric(clientInput)) {
            showAlert("Error", "El id del cliente debe ser un numero!");
            return;
        }

        if (!isNumeric(productInput)) {
            showAlert("Error", "El id del producto debe ser un numero!");
            return;
        }

        if (!isValidDate(dateInput)) {
            showAlert("Error", "La fecha debe tener un formato DD/MM/AAAA");
            return;
        }

        int exists = buyModel.insertBuy(clientInput, productInput, dateInput);

        if(exists == -1) {
            showAlert("Error", "El cliente o el producto no existe!");
        } else {
            id_client.clear();
            id_product.clear();
            date.clear();
            showAlert("Venta Añadida!", "La venta ha sido añadido con exito!");
            MainController.getInstance().refreshBuyTable();
        }
    }

    /**
     * Creates a form to delete a buy by its ID.
     *
     * @param VBox Container where the form will be added.
     */
    public void deleteBuyForm(VBox VBox){
        VBox.getChildren().clear();
        Label deleteBuyIdLabel = new Label("ID venta a borrar:");
        TextField deleteBuyIDField = new TextField();

        Button deleteBuyButton = new Button("Enviar");

        deleteBuyButton.setOnAction(e -> {
            deleteBuy(deleteBuyIDField);
        });

        VBox.getChildren().addAll(deleteBuyIdLabel, deleteBuyIDField, deleteBuyButton);
        VBox.setMargin(deleteBuyButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Deletes a buy based on the provided ID.
     *
     * @param deleteBuy TextField containing the ID of the buy to delete.
     */
    public void deleteBuy(TextField deleteBuy){
        String input = deleteBuy.getText();

        if (isNumeric(input)) {
            String BuyData = buyModel.getBuyInfo(Integer.parseInt(input));
            if (!BuyData.isEmpty()) {
                deleteBuy.clear();
                buyModel.deleteBuy(Integer.parseInt(input));
                MainController.getInstance().refreshBuyTable();
                showAlert("Venta borrada!" , "La venta con el id " + input + " ha sido borrado!");
            } else {
                showAlert("Error", "No se ha encontrado la venta");
            }
        } else {
            showAlert("Error", "El id debe ser un numero!");
        }
    }

    /**
     * Creates a form to search and modify a buy by its ID.
     *
     * @param VBox Container where the form will be added.
     */
    public void searchModifyBuyForm(VBox VBox){
        VBox.getChildren().clear();
        Label modifyBuyIdLabel = new Label("ID venta a modificar:");
        TextField BuyIDField = new TextField();

        Button searchModifyBuyButton = new Button("Enviar");

        searchModifyBuyButton.setOnAction(e -> {
            String input = BuyIDField.getText();

            if (isNumeric(input)) {
                String BuyData = buyModel.getBuyInfo(Integer.parseInt(input));
                if (!BuyData.isEmpty()) {
                    modifyBuyForm(VBox, Integer.parseInt(input));
                } else {
                    showAlert("Error", "No se ha encontrado la venta");
                }
            } else {
                showAlert("Error", "El id debe ser un numero!");
            }
        });

        VBox.getChildren().addAll(modifyBuyIdLabel, BuyIDField, searchModifyBuyButton);
        VBox.setMargin(searchModifyBuyButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Creates a form to modify a buy based on the provided ID.
     *
     * @param VBox    Container where the form will be added.
     * @param inputID ID of the buy to modify.
     */
    public void modifyBuyForm(VBox VBox, int inputID){
        VBox.getChildren().clear();
        String[] BuyData = buyModel.getBuyInfo(inputID).split(":");

        Label nameLabel = new Label("ID Cliente:");
        TextField nameField = new TextField();
        nameField.setText(BuyData[0]);

        Label descriptionLabel = new Label("ID producto:");
        TextField descriptionField = new TextField();
        descriptionField.setText(BuyData[1]);

        Label PVPLabel = new Label("Fecha:");
        TextField PVPField = new TextField();
        PVPField.setText(BuyData[2]);

        Button modifyBuyButton = new Button("Enviar");

        modifyBuyButton.setOnAction(e -> {
            modifyBuy(inputID, nameField, descriptionField, PVPField, VBox);
        });

        VBox.getChildren().addAll(nameLabel, nameField, descriptionLabel, descriptionField, PVPLabel, PVPField, modifyBuyButton);
        VBox.setMargin(modifyBuyButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Modifies a buy based on the provided information.
     *
     * @param ID         ID of the buy to modify.
     * @param id_client  TextField containing the new client ID for the buy.
     * @param id_product TextField containing the new product ID for the buy.
     * @param date       TextField containing the new date of the buy.
     * @param VBox       Container where the form is located.
     */
    public void modifyBuy(int ID,TextField id_client, TextField id_product, TextField date, VBox VBox) {
        String clientInput = id_client.getText();
        String productInput = id_product.getText();
        String dateInput = date.getText();

        if (!isNumeric(clientInput)) {
            showAlert("Error", "El id del cliente debe ser un numero!");
            return;
        }

        if (!isNumeric(productInput)) {
            showAlert("Error", "El id del producto debe ser un numero!");
            return;
        }

        if (!isValidDate(dateInput)) {
            showAlert("Error", "La fecha debe tener un formato DD/MM/AAAA");
            return;
        }


        int exists = buyModel.modifyBuy(ID,clientInput, productInput, dateInput);

        if(exists == -1) {
            showAlert("Error" , "El cliente o el producto no existe!");
        } else{
            id_client.clear();
            id_product.clear();
            date.clear();
            showAlert("Venta modificada!" , "La venta con el id " + ID + " ha sido modificada!");
            MainController.getInstance().refreshBuyTable();
            VBox.getChildren().clear();
        }

    }

    /**
     * Displays information about a buy in a pop-up window.
     *
     * @param data Information about the buy.
     */
    private void BuyInfo(String data){
        String[] formatedData = data.split(":");

        TextArea BuyeInfoTextArea = new TextArea();
        BuyeInfoTextArea.setEditable(false);

        BuyeInfoTextArea.setText("ID cliente: " + formatedData[0] + "\nID producto: " + formatedData[1] + "\nFecha: " + formatedData[2]);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(BuyeInfoTextArea);

        Scene scene = new Scene(root, 600, 400);

        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Información de la venta");
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

    /**
     * Checks if a date string has a valid format (DD/MM/YYYY).
     *
     * @param str Date string to check.
     * @return True if the date string has a valid format, false otherwise.
     */
    private boolean isValidDate(String str) {
        if (!str.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }

        String[] parts = str.split("/");

        for (String part : parts) {
            if (!part.matches("\\d+")) {
                return false;
            }
        }

        return true;
    }

}
