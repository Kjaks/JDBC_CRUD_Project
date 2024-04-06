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
    private static MainController instance;

    public MainController() {
        instance = this;
    }

    /**
     * Returns the instance of MainController, this method is used for refreshing the table in the other controllers.
     * @return The instance of MainController.
     */
    public static MainController getInstance() {
        return instance;
    }

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
    private final ObservableList<Buy> buyData = FXCollections.observableArrayList();
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
            String name = clientStringData[i + 1];
            String surname1 = clientStringData[i + 2];
            String surname2 = clientStringData[i + 3];
            String phone = clientStringData[i + 4];

            clientData.add(new Client(id, name, surname1, surname2, phone));

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
            String name = productStringData[i + 1];
            String description = productStringData[i + 2];
            Double PVP = Double.parseDouble(productStringData[i + 3]);

            productData.add(new Product(id, name, description, PVP));

        }
        productTable.setItems(productData);
    }

    @FXML
    public void refreshBuyTable(){
        String fullBuyText = buyController.getBuyInfo();

        // Limpiar la lista existente antes de agregar los nuevos clientes.
        buyData.clear();

        // Separar el texto completo en partes que representan un cliente cada una
        String[] buyStringData = fullBuyText.split(":");

        /**
         * Iterates over array and puts the data in the table
         */
        for(int i = 0; i < buyStringData.length - 1;i+= 3){
            String client_name = buyStringData[i];
            String product_name = buyStringData[i + 1];
            String buy_date = buyStringData[i + 2];

            buyData.add(new Buy(client_name, product_name, buy_date));

        }
        buyTable.setItems(buyData);
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
        clientController.searchModifyClientForm(clientVBox);
        refreshClientTable();
    }

    public void createTableButton(){
        buyController.createBuyTable();
    }

}