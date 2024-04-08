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
/**
 * The MainController class controls the main functionality of the Celia Shop application.
 * This class handles the display and manipulation of data in the TableView components.
 * @author Karolis Jakas Stirbyte
 */
public class MainController {
    private static MainController instance;

    public MainController() {
        instance = this;
    }

    /**
     * Returns the instance of MainController, used for refreshing the table in other controllers.
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
    private TableColumn<Buy, String> idBuyCol;
    @FXML
    private TableColumn<Buy, String> clientBuyCol;
    @FXML
    private TableColumn<Buy, String> productBuyCol;
    @FXML
    private TableColumn<Buy, String> productDateCol;
    @FXML
    private final ObservableList<Buy> buyData = FXCollections.observableArrayList();
    @FXML
    public VBox clientVBox;
    @FXML
    public VBox productVBox;
    @FXML
    public VBox buyVBox;
    buyController buyController = new buyController();
    clientController clientController = new clientController();
    productController productController = new productController();

    /**
     * Initializes the MainController, setting up the TableView columns and refreshing the tables.
     */
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

        idBuyCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientBuyCol.setCellValueFactory(new PropertyValueFactory<>("clientBuy"));
        productBuyCol.setCellValueFactory(new PropertyValueFactory<>("productBuy"));
        productDateCol.setCellValueFactory(new PropertyValueFactory<>("buyDate"));
        refreshBuyTable();
    }

    /**
     * Refreshes the client table by fetching data from the database and populating the table.
     */
    @FXML
    public void refreshClientTable() {
        String fullClientText = clientController.getClientsInfo();

        clientData.clear();

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

    /**
     * Refreshes the product table by fetching data from the database and populating the table.
     */
    @FXML
    public void refreshProductTable() {
        String fullProductText = productController.getProductsInfo();

        productData.clear();

        String[] productStringData = fullProductText.split(":");

        for(int i = 0; i < productStringData.length - 1;i+= 4){
            int id = Integer.parseInt(productStringData[i]);
            String name = productStringData[i + 1];
            String description = productStringData[i + 2];
            Double PVP = Double.parseDouble(productStringData[i + 3]);

            productData.add(new Product(id, name, description, PVP));

        }
        productTable.setItems(productData);
    }

    /**
     * Refreshes the buy table by fetching data from the database and populating the table.
     */
    @FXML
    public void refreshBuyTable(){
        String fullBuyText = buyController.getBuyInfo();

        buyData.clear();

        String[] buyStringData = fullBuyText.split(":");

        for(int i = 0; i < buyStringData.length - 1;i+= 4){
            int id = Integer.parseInt(buyStringData[i]);
            String client_name = buyStringData[i + 1];
            String product_name = buyStringData[i + 2];
            String buy_date = buyStringData[i + 3];

            buyData.add(new Buy(id, client_name, product_name, buy_date));

        }
        buyTable.setItems(buyData);
    }

    /**
     * Opens a form to consult client information.
     */
    public void consultClient(){
        clientController.consultFormClient(clientVBox);
    }

    /**
     * Opens a form to add a new client.
     * Refreshes the client table after adding.
     */
    public void addClient(){
        clientController.addClientForm(clientVBox);
        refreshClientTable();
    }

    /**
     * Opens a form to delete a client.
     * Refreshes the client table after deletion.
     */
    public void deleteClient(){
        clientController.deleteClientForm(clientVBox);
        refreshClientTable();
    }

    /**
     * Opens a form to modify client information.
     * Refreshes the client table after modification.
     */
    public void modifyClient(){
        clientController.searchModifyClientForm(clientVBox);
        refreshClientTable();
    }

    /**
     * Opens a form to consult product information.
     */
    public void consultProduct(){
        productController.consultFormProduct(productVBox);
    }

    /**
     * Opens a form to add a new product.
     * Refreshes the product table after adding.
     */
    public void addProduct(){
        productController.addProductForm(productVBox);
        refreshProductTable();
    }

    /**
     * Opens a form to delete a product.
     * Refreshes the product table after deletion.
     */
    public void deleteProduct(){
        productController.deleteProductForm(productVBox);
        refreshProductTable();
    }

    /**
     * Opens a form to modify product information.
     * Refreshes the product table after modification.
     */
    public void modifyProduct(){
        productController.searchModifyProductForm(productVBox);
        refreshProductTable();
    }

    /**
     * Opens a form to consult buy information.
     */
    public void consultBuy(){
        buyController.consultFormBuy(buyVBox);
    }

    /**
     * Opens a form to add a new buy.
     * Refreshes the buy table after adding.
     */
    public void addBuy(){
        buyController.addBuyForm(buyVBox);
        refreshBuyTable();
    }

    /**
     * Opens a form to delete a buy.
     * Refreshes the buy table after deletion.
     */
    public void deleteBuy(){
        buyController.deleteBuyForm(buyVBox);
        refreshBuyTable();
    }

    /**
     * Opens a form to modify buy information.
     * Refreshes the buy table after modification.
     */
    public void modifyBuy(){
        buyController.searchModifyBuyForm(buyVBox);
        refreshBuyTable();
    }

    /**
     * Creates a table for storing buy information. This method is requested by the teacher.
     */
    public void createTableButton(){
        buyController.createBuyTable();
    }

}