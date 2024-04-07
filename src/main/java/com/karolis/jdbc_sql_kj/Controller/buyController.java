package com.karolis.jdbc_sql_kj.Controller;

import Models.buyModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class buyController {
    buyModel buyModel = new buyModel();
    public void createBuyTable(){
        int result = 0;

        result = buyModel.createTable();
        if(result == -1) System.out.println("Error BBDD!");
        else System.out.println("Tabla creada!");
    }

    public String getBuyInfo() {
        return buyModel.getBuys();
    }

    public void consultFormBuy(VBox VBox){
        VBox.getChildren().clear();
        Label consultBuyIdLabel = new Label("ID a consultar:");
        TextField consultBuyIDField = new TextField();

        // Crear un botón
        Button consultBuyButton = new Button("Enviar");

        // Definir el evento onAction del botón
        consultBuyButton.setOnAction(e ->
                consultBuy(consultBuyIDField)
        );

        VBox.getChildren().addAll(consultBuyIdLabel, consultBuyIDField, consultBuyButton);
        VBox.setMargin(consultBuyButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

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

    public void addBuyForm(VBox VBox){
        VBox.getChildren().clear();
        Label clientLabel = new Label("ID_cliente");
        TextField clientField = new TextField();

        Label productLabel = new Label("ID_producto:");
        TextField productField = new TextField();

        Label dateLabel = new Label("Fecha:");
        TextField dateField = new TextField();

        // Crear un botón
        Button addBuyButton = new Button("Enviar");

        // Definir el evento onAction del botón
        addBuyButton.setOnAction(e -> {
            addBuy(clientField, productField, dateField);
        });

        VBox.getChildren().addAll(clientLabel, clientField, productLabel, productField, dateLabel, dateField, addBuyButton);
        VBox.setMargin(addBuyButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

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

    public void deleteBuyForm(VBox VBox){
        VBox.getChildren().clear();
        Label deleteBuyIdLabel = new Label("ID venta a borrar:");
        TextField deleteBuyIDField = new TextField();

        // Crear un botón
        Button deleteBuyButton = new Button("Enviar");

        // Definir el evento onAction del botón
        deleteBuyButton.setOnAction(e -> {
            deleteBuy(deleteBuyIDField);
        });

        VBox.getChildren().addAll(deleteBuyIdLabel, deleteBuyIDField, deleteBuyButton);
        VBox.setMargin(deleteBuyButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

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

    public void searchModifyBuyForm(VBox VBox){
        VBox.getChildren().clear();
        Label modifyBuyIdLabel = new Label("ID venta a modificar:");
        TextField BuyIDField = new TextField();

        // Crear un botón
        Button searchModifyBuyButton = new Button("Enviar");

        // Definir el evento onAction del botón
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
        VBox.setMargin(searchModifyBuyButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

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

        // Crear un botón
        Button modifyBuyButton = new Button("Enviar");

        // Definir el evento onAction del botón
        modifyBuyButton.setOnAction(e -> {
            modifyBuy(inputID, nameField, descriptionField, PVPField, VBox);
        });

        VBox.getChildren().addAll(nameLabel, nameField, descriptionLabel, descriptionField, PVPLabel, PVPField, modifyBuyButton);
        VBox.setMargin(modifyBuyButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

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

    private void BuyInfo(String data){
        String[] formatedData = data.split(":");

        // Crear un TextArea para mostrar la información del Buye
        TextArea BuyeInfoTextArea = new TextArea();
        BuyeInfoTextArea.setEditable(false);

        // Configurar el TextArea con la información del Buye
        BuyeInfoTextArea.setText("ID cliente: " + formatedData[0] + "\nID producto: " + formatedData[1] + "\nFecha: " + formatedData[2]);

        // Crear un diseño para la ventana emergente
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(BuyeInfoTextArea);

        // Configurar la escena
        Scene scene = new Scene(root, 600, 400);

        // Configurar el nuevo Stage como modal
        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Información de la venta");
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
