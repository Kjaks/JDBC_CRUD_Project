package com.karolis.jdbc_sql_kj.TableInfo;

public class Client {
    private final String client_name,surname1, surname2, phone;
    private final int id ;

    public Client(int id, String client_name, String surname1, String surname2, String phone) {
        this.id = id;
        this.client_name = client_name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.phone = phone;
    }

    public String getClient_name() {
        return client_name;
    }

    public String getSurname1() {
        return surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }
}
