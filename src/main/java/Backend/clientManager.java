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

            for (int i = 0; i < clientsArrSize; i++) {
                int clientID = clients.getInt(1);
                String clientName = clients.getString(2);
                boolean loyalty = clients.getBoolean(3);
                
                Client c = new Client(clientName, clientID, loyalty);
                clientsArr[i] = c;
            }
            
            //Checks to see if the client has a registered account in the clientsSales table if not make one     

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(clientManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(clientManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
