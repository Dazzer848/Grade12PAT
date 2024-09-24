/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.sql.ResultSet;
import DBMS.DB;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dazzl
 */
public class inventoryManager {
    private Part[] parts;
    private int size;

    
    public inventoryManager(){
        try {
            
            // Connects to the Database
            DB.connect();
            
            //Returns the number of Items in the Inventory
            ResultSet q1 = DB.query("SELECT COUNT(*) FROM sys.inventory;");
            q1.next();
            
            // Stores the number of Items in the inventory 
            int numItems = q1.getInt(1);
            
            // Creates an Array with a max size of the amount ofitems in the Database
            parts = new Part[numItems];
            
            //Selects all the parts from the database
            ResultSet table = DB.query("SELECT * FROM sys.parts;");
            
            //Loops through the table and populates all the required fields of the Parts
            while (table.next()) {
                
                //Retrieves the Fields for the PersonObject
                int partID = table.getInt(1);
                String partName = table.getString(2);
                int price = table.getInt(3);
                String catogory = table.getString(4);
                
                
                //Creates the New Person
                Part p = new Part(partID, partName, price, catogory);
                
                //Adds the person to the array and increases the count
                parts[size] = p;
                size++;
                
                
                
                
            }       } catch (ClassNotFoundException ex) {
            Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}



