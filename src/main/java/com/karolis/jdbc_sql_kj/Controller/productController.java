package com.karolis.jdbc_sql_kj.Controller;

import Models.productModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class productController {
    productModel productModel = new productModel();
    public String getProductsInfo(){
        return productModel.getProducts();
    }

    public void consultFormProduct(VBox VBox){
        VBox.getChildren().clear();
        Label consultProductIdLabel = new Label("ID a consultar:");
        TextField consultProductIDField = new TextField();

        // Crear un botón
        Button consultProductButton = new Button("Enviar");

        // Definir el evento onAction del botón
        consultProductButton.setOnAction(e ->
                consultProduct(consultProductIDField)
        );

        VBox.getChildren().addAll(consultProductIdLabel, consultProductIDField, consultProductButton);
        VBox.setMargin(consultProductButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void consultProduct(TextField consultProductIDField) {
        String input = consultProductIDField.getText();

        if (isNumeric(input)) {
            String ProductData = productModel.getProductInfo(Integer.parseInt(input));
            if (!ProductData.isEmpty()) {
                consultProductIDField.clear();
                ProductInfo(ProductData);
            } else {
                showAlert("Error", "No se ha encontrado el Producto");
            }
        } else {
            showAlert("Error", "El id debe ser un numero!");
        }
    }

    public void addProductForm(VBox VBox){
        VBox.getChildren().clear();
        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();

        Label descriptionLabel = new Label("Descripcion:");
        TextField descriptionField = new TextField();

        Label PVPLabel = new Label("PVP:");
        TextField PVPField = new TextField();

        // Crear un botón
        Button addProductButton = new Button("Enviar");

        // Definir el evento onAction del botón
        addProductButton.setOnAction(e -> {
            addProduct(nameField, descriptionField, PVPField);
        });

        VBox.getChildren().addAll(nameLabel, nameField, descriptionLabel, descriptionField, PVPLabel, PVPField, addProductButton);
        VBox.setMargin(addProductButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void addProduct(TextField name, TextField description, TextField PVP) {
        String nameInput = name.getText();
        String descriptionInput = description.getText();
        String PVPInput = PVP.getText();

        if (nameInput.length() >= 100 || nameInput.isEmpty() || isNumeric(nameInput)) {
            showAlert("Error", "El nombre debe tener entre 1 y 100 caracteres y deben tener al menos una letra!");
            return;
        }

        if ((descriptionInput.isEmpty()) || isNumeric(descriptionInput)) {
            showAlert("Error", "El producto tiene que tener descripcion! Y tiene que tener al menos una letra!");
            return;
        }

        Double PVPtype = 0.0;
        try {
            PVPtype = Double.parseDouble(PVPInput); // Intenta convertir la cadena a double
        } catch (NumberFormatException e) {
            showAlert("Error", "El PVP debe ser decimal!");
            return;
        }


        name.clear();
        description.clear();
        PVP.clear();
        productModel.insertProduct(nameInput, descriptionInput, PVPtype);
        showAlert("Producte añadido!" , "El Producto ha sido añadido con exito!");
        MainController.getInstance().refreshProductTable();
        MainController.getInstance().refreshBuyTable();

    }

    public void deleteProductForm(VBox VBox){
        VBox.getChildren().clear();
        Label deleteProductIdLabel = new Label("ID Producto a borrar:");
        TextField deleteProductIDField = new TextField();

        // Crear un botón
        Button deleteProductButton = new Button("Enviar");

        // Definir el evento onAction del botón
        deleteProductButton.setOnAction(e -> {
            deleteProduct(deleteProductIDField);
        });

        VBox.getChildren().addAll(deleteProductIdLabel, deleteProductIDField, deleteProductButton);
        VBox.setMargin(deleteProductButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void deleteProduct(TextField deleteProduct){
        String input = deleteProduct.getText();

        if (isNumeric(input)) {
            String ProductData = productModel.getProductInfo(Integer.parseInt(input));
            if (!ProductData.isEmpty()) {
                deleteProduct.clear();
                productModel.deleteProduct(Integer.parseInt(input));
                MainController.getInstance().refreshProductTable();
                MainController.getInstance().refreshBuyTable();
                showAlert("Producte borrado!" , "El Producto con el id " + input + " ha sido borrado!");
            } else {
                showAlert("Error", "No se ha encontrado el Producto");
            }
        } else {
            showAlert("Error", "El id debe ser un numero!");
        }
    }

    public void searchModifyProductForm(VBox VBox){
        VBox.getChildren().clear();
        Label modifyProductIdLabel = new Label("ID Producto a modificar:");
        TextField ProductIDField = new TextField();

        // Crear un botón
        Button searchModifyProductButton = new Button("Enviar");

        // Definir el evento onAction del botón
        searchModifyProductButton.setOnAction(e -> {
            String input = ProductIDField.getText();

            if (isNumeric(input)) {
                String ProductData = productModel.getProductInfo(Integer.parseInt(input));
                if (!ProductData.isEmpty()) {
                    modifyProductForm(VBox, Integer.parseInt(input));
                } else {
                    showAlert("Error", "No se ha encontrado el Producto");
                }
            } else {
                showAlert("Error", "El id debe ser un numero!");
            }
        });

        VBox.getChildren().addAll(modifyProductIdLabel, ProductIDField, searchModifyProductButton);
        VBox.setMargin(searchModifyProductButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void modifyProductForm(VBox VBox, int inputID){
        VBox.getChildren().clear();
        String[] ProductData = productModel.getProductInfo(inputID).split(":");

        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();
        nameField.setText(ProductData[0]);

        Label descriptionLabel = new Label("Descripcion:");
        TextField descriptionField = new TextField();
        descriptionField.setText(ProductData[1]);

        Label PVPLabel = new Label("PVP:");
        TextField PVPField = new TextField();
        PVPField.setText(ProductData[2]);

        // Crear un botón
        Button modifyProductButton = new Button("Enviar");

        // Definir el evento onAction del botón
        modifyProductButton.setOnAction(e -> {
            modifyProduct(inputID, nameField, descriptionField, PVPField, VBox);
        });

        VBox.getChildren().addAll(nameLabel, nameField, descriptionLabel, descriptionField, PVPLabel, PVPField, modifyProductButton);
        VBox.setMargin(modifyProductButton, new Insets(10, 0, 0, 0)); // top, right, bottom, left
    }

    public void modifyProduct(int ID,TextField name, TextField description, TextField PVP, VBox VBox) {
        String nameInput = name.getText();
        String descriptionInput = description.getText();
        String PVPInput = PVP.getText();

        if (nameInput.length() >= 100 || nameInput.isEmpty() || isNumeric(nameInput)) {
            showAlert("Error", "El nombre debe tener entre 1 y 100 caracteres y deben tener al menos una letra!");
            return;
        }

        if ((descriptionInput.isEmpty()) || isNumeric(descriptionInput)) {
            showAlert("Error", "El producto tiene que tener descripcion! Y tiene que tener al menos una letra!");
            return;
        }

        Double PVPtype = 0.0;
        try {
            PVPtype = Double.parseDouble(PVPInput); // Intenta convertir la cadena a double
        } catch (NumberFormatException e) {
            showAlert("Error", "El PVP debe ser decimal!");
            return;
        }

        name.clear();
        description.clear();
        PVP.clear();
        productModel.modifyProduct(ID,nameInput, descriptionInput, PVPtype);
        showAlert("Producte modificado!" , "El Producto con el id " + ID + " ha sido modificado!");
        MainController.getInstance().refreshProductTable();
        VBox.getChildren().clear();

    }

    private void ProductInfo(String data){
        String[] formatedData = data.split(":");

        // Crear un TextArea para mostrar la información del Producte
        TextArea ProducteInfoTextArea = new TextArea();
        ProducteInfoTextArea.setEditable(false);

        // Configurar el TextArea con la información del Producte
        ProducteInfoTextArea.setText("Nombre producto: " + formatedData[0] + "\nDescripcion del producto: " + formatedData[1] + "\nPVP: " + formatedData[2]);

        // Crear un diseño para la ventana emergente
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(ProducteInfoTextArea);

        // Configurar la escena
        Scene scene = new Scene(root, 600, 400);

        // Configurar el nuevo Stage como modal
        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Información del Producte");
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
