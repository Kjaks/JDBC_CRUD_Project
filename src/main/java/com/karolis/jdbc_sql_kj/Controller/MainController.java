package com.karolis.jdbc_sql_kj.Controller;

import com.karolis.jdbc_sql_kj.TableInfo.Client;
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
    public VBox clientVBox;
    com.karolis.jdbc_sql_kj.Controller.buyController buyController = new buyController();
    com.karolis.jdbc_sql_kj.Controller.clientController clientController = new clientController();
    com.karolis.jdbc_sql_kj.Controller.productController productController = new productController();

    @FXML
    public void initialize() {
        clientIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientNameCol.setCellValueFactory(new PropertyValueFactory<>("client_name"));
        clientSurname1Col.setCellValueFactory(new PropertyValueFactory<>("surname1"));
        clientSurname2Col.setCellValueFactory(new PropertyValueFactory<>("surname2"));
        clientPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        refreshClientTable();
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


    public void consultClient(){
        clientController.consultFormClient(clientVBox);
    }

    public void addClient(){
        clientController.addClientForm(clientVBox);
    }

    public void deleteClient(){
        clientController.deleteClientForm(clientVBox);
    }

    public void modifyClient(){
        clientController.modifyClient(clientVBox);
    }

    public void createTableButton(){
        buyController.createBuyTable();
    }

}