package com.karolis.jdbc_sql_kj.Controller;

import com.karolis.jdbc_sql_kj.TableInfo.Buy;
import com.karolis.jdbc_sql_kj.TableInfo.Client;
import com.karolis.jdbc_sql_kj.TableInfo.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<Client, Integer> clientIDCol;
    @FXML
    private TableColumn<Client, String> clientNameCol;
    @FXML
    private TableColumn<Client, String> clientSurname1Col;
    @FXML
    private TableColumn<Client, String> clientSurname2Col;
    @FXML
    private TableColumn<Client, String> clientPhoneCol;
    @FXML
    private final ObservableList<Client> clientData = FXCollections.observableArrayList();

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, String> productDescriptionCol;
    @FXML
    private TableColumn<Product, Double> productPVPCol;
    @FXML
    private final ObservableList<Product> productData = FXCollections.observableArrayList();

    @FXML
    private TableView<Buy> buyTable;
    @FXML
    private TableColumn<Buy, String> clientBuyCol;
    @FXML
    private TableColumn<Buy, String> productBuyCol;
    @FXML
    private TableColumn<Buy, Double> productDateCol;
    @FXML
    private final ObservableList<Product> buyData = FXCollections.observableArrayList();
    @FXML
    public VBox clientVBox;
    buyController buyController = new buyController();
    clientController clientController = new clientController();
    productController productController = new productController();

    @FXML
    public void initialize() {
        clientIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientNameCol.setCellValueFactory(new PropertyValueFactory<>("client_name"));
        clientSurname1Col.setCellValueFactory(new PropertyValueFactory<>("surname1"));
        clientSurname2Col.setCellValueFactory(new PropertyValueFactory<>("surname2"));
        clientPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        refreshClientTable();

        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        productPVPCol.setCellValueFactory(new PropertyValueFactory<>("productPVP"));
        refreshProductTable();

        clientBuyCol.setCellValueFactory(new PropertyValueFactory<>("clientBuy"));
        productBuyCol.setCellValueFactory(new PropertyValueFactory<>("productBuy"));
        productDateCol.setCellValueFactory(new PropertyValueFactory<>("buyDate"));
        refreshBuyTable();
    }

    @FXML
    public void refreshClientTable() {
        String fullClientText = clientController.getClientsInfo();

        // Limpiar la lista existente antes de agregar los nuevos clientes.
        clientData.clear();

        // Separar el texto completo en partes que representan un cliente cada una
        String[] clientStringData = fullClientText.split(":");

        /**
         * Iterates over array and puts the data in the table
         */
        for(int i = 0; i < clientStringData.length - 1;i+= 5){
            int id = Integer.parseInt(clientStringData[i]);
            String nombre = clientStringData[i + 1];
            String apellido1 = clientStringData[i + 2];
            String apellido2 = clientStringData[i + 3];
            String telefono = clientStringData[i + 4];

            clientData.add(new Client(id, nombre, apellido1, apellido2, telefono));

        }
        clientTable.setItems(clientData);
    }

    @FXML
    public void refreshProductTable() {
        String fullProductText = productController.getProductsInfo();

        // Limpiar la lista existente antes de agregar los nuevos clientes.
        productData.clear();

        // Separar el texto completo en partes que representan un cliente cada una
        String[] productStringData = fullProductText.split(":");

        /**
         * Iterates over array and puts the data in the table
         */
        for(int i = 0; i < productStringData.length - 1;i+= 4){
            int id = Integer.parseInt(productStringData[i]);
            String nombre = productStringData[i + 1];
            String descripcion = productStringData[i + 2];
            Double PVP = Double.parseDouble(productStringData[i + 3]);

            productData.add(new Product(id, nombre, descripcion, PVP));

        }
        productTable.setItems(productData);
    }

    @FXML
    public void refreshBuyTable(){

    }


    public void consultClient(){
        clientController.consultFormClient(clientVBox);
    }

    public void addClient(){
        clientController.addClientForm(clientVBox);
        refreshClientTable();
    }

    public void deleteClient(){
        clientController.deleteClientForm(clientVBox);
        refreshClientTable();
    }

    public void modifyClient(){
        clientController.modifyClient(clientVBox);
        refreshClientTable();
    }

    public void createTableButton(){
        buyController.createBuyTable();
    }

}