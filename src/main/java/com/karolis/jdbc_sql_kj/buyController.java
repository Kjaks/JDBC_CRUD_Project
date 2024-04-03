package com.karolis.jdbc_sql_kj;

import Models.buyModel;

public class buyController {

    buyModel buyModel = new buyModel();
    public void createBuyTable(){
        int result = 0;

        result = buyModel.createTable();
        if(result == -1) System.out.println("Error BBDD!");
        else System.out.println("Tabla creada!");
    }
}
