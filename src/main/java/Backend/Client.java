/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import DBMS.DB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darre
 */
public class Client {
    //Initializes global variables
    private String name;
    private int clientID;
    private boolean loyalty;
    private int totalSpent;

    //Constructor
    public Client(String name, int clientID, boolean loyalty, int inTotalSpent) {
        this.name = name;
        this.clientID = clientID;
        this.loyalty = loyalty;
        this.totalSpent = inTotalSpent;
    }
    
    // Returns the interger of the total spent by the cleint
    public int getTotalSpent() {
        int t = totalSpent;
        return totalSpent;
    }
    //Returns the String of the clients name
    public String getName() {
        return name;
    }
    // Returns the Integer of the clientsID
    public int getClientID() {
        return clientID;
    }

    //Returns the clients loyalty boolean
    public boolean isLoyalty() {
        return loyalty;
    }
    
    //Updates the amount of money a clinet has Spent
    public void updateTotalSpent(int inSpent) {
        this.totalSpent = totalSpent + inSpent;
    }
    
    //Set's the loyalty of the cleint
    public void setLoyalty(boolean inLoyalty) {
        this.loyalty = loyalty;
    }
    
    
    
    
    
}
