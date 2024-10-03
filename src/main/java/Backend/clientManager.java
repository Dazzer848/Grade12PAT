/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import DBMS.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dazzl
 */
public class clientManager {

    private int size;
    private Client[] clientsArr;

    public clientManager() {
        try {

            // Connects to the database
            DB.connect();
            
            //Get's the size of the clients
            ResultSet clientsArrSizeTable = DB.query("SELECT count(ClientID) FROM sys.clients;");
            clientsArrSizeTable.next();
            int clientsArrSize = clientsArrSizeTable.getInt(1);

            //Makes a client size array
            clientsArr = new Client[clientsArrSize];

            //Gathers all the client data
            ResultSet clients = DB.query("SELECT * FROM sys.clients;");
            clients.next();

            //Populates the client object
            for (int i = 0; i < clientsArrSize; i++) {
                int clientID = clients.getInt(1);
                String clientName = clients.getString(2);
                boolean loyalty = clients.getBoolean(3);
                int totalSpent = clients.getInt(4);
                
                // Checks if the client is elligable for loyalty or has loyalty alrady
                if(totalSpent > 50000 || loyalty == true){
                    DB.update("UPDATE sys.clients SET Loyalty = 1 WHERE (ClientID = " + clientID + ");");
                }
               
                
                clients.next();
                //Makes a new Client Object and adds it to an array
                Client c = new Client(clientName, clientID, loyalty, totalSpent);
                clientsArr[i] = c;
                size++;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(clientManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(clientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    //A method used to locate a client object by it's clientID
    public Client findClientById(int inCID){
        
        //Searches throught he array of clients
        for(int i = 0; i < size;i++){
            
            //Get's the client ID
            int clientsID = clientsArr[i].getClientID();
            
            //Of the client ID mathes that of the ClientID we are looking for
            if(clientsArr[i].getClientID() == inCID){
                //Return that client object
                return clientsArr[i];
            }
        }
        //If nothing is found return null
        return null;
    }
    
   //updates the clients loyalty status
   public void setLoyalty(boolean inLoyalty, int inCID) {
        try {
            
            //Finds the clinet in question
            Client c = findClientById(inCID);
            
            //Updates it's loyalty to the status
            c.setLoyalty(inLoyalty);
            
            //Updates the database to reflect this change
            DB.update("UPDATE sys.clients SET Loyalty = " + inLoyalty + " WHERE (ClientID = " + inCID + ");");
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
