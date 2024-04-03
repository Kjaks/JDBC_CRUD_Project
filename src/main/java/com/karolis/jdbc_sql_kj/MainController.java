package com.karolis.jdbc_sql_kj;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainController {
    @FXML
    public VBox clientVBox;
    buyController buyController = new buyController();
    clientController clientController = new clientController();
    productController productController = new productController();


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