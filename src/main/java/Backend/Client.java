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
    private String name;
    private int clientID;
    private boolean loyalty;
    private int totalSpent;

    public Client(String name, int clientID, boolean loyalty, int inTotalSpent) {
        this.name = name;
        this.clientID = clientID;
        this.loyalty = loyalty;
        this.totalSpent = inTotalSpent;
    }

    public int getTotalSpent() {
        int t = totalSpent;
        return totalSpent;
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

    public void updateTotalSpent(int inSpent) {
        this.totalSpent = totalSpent + inSpent;
    }

    public void setLoyalty(boolean inLoyalty) {
        this.loyalty = loyalty;
    }
    
    
    
    
    
}
