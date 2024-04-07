package com.karolis.jdbc_sql_kj.TableInfo;

/**
 * The Client class represents a client entity in the Celia Shop application.
 * This class stores information about a client, including their ID, name, surnames, and phone number.
 * @author Karolis Jakas Stirbyte
 */
public class Client {
    private final String client_name;
    private final String surname1;
    private final String surname2;
    private final String phone;
    private final int id;

    /**
     * Constructs a new Client object with the specified attributes.
     *
     * @param id The ID of the client.
     * @param client_name The name of the client.
     * @param surname1 The first surname of the client.
     * @param surname2 The second surname of the client.
     * @param phone The phone number of the client.
     */
    public Client(int id, String client_name, String surname1, String surname2, String phone) {
        this.id = id;
        this.client_name = client_name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.phone = phone;
    }

    /**
     * Retrieves the name of the client.
     *
     * @return The name of the client.
     */
    public String getClient_name() {
        return client_name;
    }

    /**
     * Retrieves the first surname of the client.
     *
     * @return The first surname of the client.
     */
    public String getSurname1() {
        return surname1;
    }

    /**
     * Retrieves the second surname of the client.
     *
     * @return The second surname of the client.
     */
    public String getSurname2() {
        return surname2;
    }

    /**
     * Retrieves the phone number of the client.
     *
     * @return The phone number of the client.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Retrieves the ID of the client.
     *
     * @return The ID of the client.
     */
    public int getId() {
        return id;
    }
}

