package com.karolis.jdbc_sql_kj.Controller;

import Models.productModel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Controller class for managing product-related operations.
 * @author Karolis Jakas Stirbyte
 */
public class productController {

    productModel productModel = new productModel();
    /**
     * Retrieves information of all products, for the tableView
     *
     * @return Information of the products.
     */
    public String getProductsInfo(){
        return productModel.getProducts();
    }

    /**
     * Creates a form to consult a product by its ID.
     *
     * @param VBox Container where the form will be added.
     */
    public void consultFormProduct(VBox VBox){
        VBox.getChildren().clear();
        Label consultProductIdLabel = new Label("ID a consultar:");
        TextField consultProductIDField = new TextField();

        Button consultProductButton = new Button("Enviar");

        consultProductButton.setOnAction(e ->
                consultProduct(consultProductIDField)
        );

        VBox.getChildren().addAll(consultProductIdLabel, consultProductIDField, consultProductButton);
        VBox.setMargin(consultProductButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Performs the action of consulting a product by its ID and give the information about a product.
     *
     * @param consultProductIDField TextField containing the product ID to consult.
     */
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

    /**
     * Creates a form to add a new product.
     *
     * @param VBox Container where the form will be added.
     */
    public void addProductForm(VBox VBox){
        VBox.getChildren().clear();
        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();

        Label descriptionLabel = new Label("Descripcion:");
        TextField descriptionField = new TextField();

        Label PVPLabel = new Label("PVP:");
        TextField PVPField = new TextField();

        Button addProductButton = new Button("Enviar");

        addProductButton.setOnAction(e -> {
            addProduct(nameField, descriptionField, PVPField);
        });

        VBox.getChildren().addAll(nameLabel, nameField, descriptionLabel, descriptionField, PVPLabel, PVPField, addProductButton);
        VBox.setMargin(addProductButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Adds a new product based on the provided information.
     *
     * @param name         TextField containing the name of the product.
     * @param description  TextField containing the description of the product.
     * @param PVP          TextField containing the price of the product.
     */
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
            PVPtype = Double.parseDouble(PVPInput);
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

    /**
     * Creates a form to delete a product by its ID.
     *
     * @param VBox Container where the form will be added.
     */
    public void deleteProductForm(VBox VBox){
        VBox.getChildren().clear();
        Label deleteProductIdLabel = new Label("ID Producto a borrar:");
        TextField deleteProductIDField = new TextField();

        Button deleteProductButton = new Button("Enviar");

        deleteProductButton.setOnAction(e -> {
            deleteProduct(deleteProductIDField);
        });

        VBox.getChildren().addAll(deleteProductIdLabel, deleteProductIDField, deleteProductButton);
        VBox.setMargin(deleteProductButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Deletes a product based on the provided ID.
     *
     * @param deleteProduct TextField containing the ID of the product to delete.
     */
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

    /**
     * Creates a form to search and modify a product by its ID.
     *
     * @param VBox Container where the form will be added.
     */
    public void searchModifyProductForm(VBox VBox){
        VBox.getChildren().clear();
        Label modifyProductIdLabel = new Label("ID Producto a modificar:");
        TextField ProductIDField = new TextField();

        Button searchModifyProductButton = new Button("Enviar");

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
        VBox.setMargin(searchModifyProductButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Creates a form to modify a product based on the provided ID.
     *
     * @param VBox     Container where the form will be added.
     * @param inputID  ID of the product to modify.
     */
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

        Button modifyProductButton = new Button("Enviar");

        modifyProductButton.setOnAction(e -> {
            modifyProduct(inputID, nameField, descriptionField, PVPField, VBox);
        });

        VBox.getChildren().addAll(nameLabel, nameField, descriptionLabel, descriptionField, PVPLabel, PVPField, modifyProductButton);
        VBox.setMargin(modifyProductButton, new Insets(10, 0, 0, 0));
    }

    /**
     * Modifies a product based on the provided information.
     *
     * @param ID          ID of the product to modify.
     * @param name        TextField containing the new name of the product.
     * @param description TextField containing the new description of the product.
     * @param PVP         TextField containing the new price of the product.
     * @param VBox        Container where the form is located.
     */
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
            PVPtype = Double.parseDouble(PVPInput);
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

    /**
     * Displays information about a product in a pop-up window.
     *
     * @param data Information about the product.
     */
    private void ProductInfo(String data){
        String[] formatedData = data.split(":");

        TextArea ProducteInfoTextArea = new TextArea();
        ProducteInfoTextArea.setEditable(false);

        ProducteInfoTextArea.setText("Nombre producto: " + formatedData[0] + "\nDescripcion del producto: " + formatedData[1] + "\nPVP: " + formatedData[2]);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(ProducteInfoTextArea);

        Scene scene = new Scene(root, 600, 400);

        Stage popUpStage = new Stage();
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setTitle("Información del Producte");
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
