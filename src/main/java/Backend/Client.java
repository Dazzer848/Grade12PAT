/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author darre
 */
public class Client {
    private String name;
    private int clientID;
    private boolean loyalty;

    public Client(String name, int clientID, boolean loyalty) {
        this.name = name;
        this.clientID = clientID;
        this.loyalty = loyalty;
    }

    public String getName() {
        return name;
    }

    public int getClientID() {
        return clientID;
    }

    public boolean isLoyalty() {
        return loyalty;
    }
    
    
    
}
