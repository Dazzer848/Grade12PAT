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
                
                if(totalSpent > 50000 || loyalty == true){
                    DB.update("UPDATE sys.clients SET Loyalty = 1 WHERE (ClientID = " + clientID + ");");
                }
               
                clients.next();
                
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
    
    public Client findClientById(int inCID){
        for(int i = 0; i < size;i++){
            int clientsID = clientsArr[i].getClientID();
            if(clientsArr[i].getClientID() == inCID){
                return clientsArr[i];
            }
        }
        return null;
    }
    
   public void setLoyalty(boolean inLoyalty, int inCID) {
        try {
            Client c = findClientById(inCID);
            c.setLoyalty(inLoyalty);
            
            DB.update("UPDATE sys.clients SET Loyalty = " + inLoyalty + " WHERE (ClientID = " + inCID + ");");
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
