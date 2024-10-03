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
    
    //Global variables of the class
    private Part[] parts;
    private int size;

    
    public inventoryManager(){
    try {
        
        // Connect to the Database
        DB.connect();

        // Get the total number of parts
        ResultSet countResult = DB.query("SELECT COUNT(*) FROM sys.parts;");
        countResult.next();
        int numItems = countResult.getInt(1);
        countResult.close();

        // Initialize the parts array to the amount of parts in the table
        parts = new Part[numItems];
        size = 0;

        //The SQL query to obtain the required data for the upcoming operations
        String query = "SELECT parts.part_id, parts.part_name, parts.price, parts.category, "
                     + "COALESCE(inventory.quantity, 0) AS quantity "
                     + "FROM sys.parts "
                     + "LEFT JOIN sys.inventory ON parts.part_id = inventory.part_id";

        // Execute the query
        ResultSet table = DB.query(query);

        // Loop through the result set and populate the parts array
        while (table.next()) {
            
            //Initiliazes the required parts variables and assigns their values
            int partID = table.getInt("part_id");
            String partName = table.getString("part_name");
            int price = table.getInt("price");
            String catogory = table.getString("category");
            int quantity = table.getInt("quantity");

            //Creates the new part which is going to be stored in the parts array
            Part p = new Part(partID, partName, price, catogory, quantity);

            //Stores the parts array
            parts[size] = p;
            size++;
        }

        // Closes the table
        table.close();

    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
    
    // A method which updates the quantity of the parts in the inventory.
    public boolean updatePartQty(int inPID, int inQty){
        
    // Finds the part in question utalizing the method in the Part's class, this then returns the correct part based off of the PartID
    Part p = findPartByID(inPID);
    
    //If the part is correctly located and does infact exist in the databse, return it's quantity
    if (p != null) {
        return p.updateQuantity(inQty);
        
    }
    
    //If the part does not exist supply an error
    
    else {
        System.err.println("Part with ID " + inPID + " not found in inventoryManager.");
        return false;
    }
    
    }
    
    
  //Finds a part based off of it's ID
  public Part findPartByID(int partID) {
      
      // Basic search
    for (int i = 0; i < size; i++) {
        
        //If the part in the arry has the same partID, return it. 
        if (parts[i].getPartID() == partID) {
            return parts[i];
        }
    }
    //If nothign is found return no Object.
    return null;
}
    public boolean deletePart(int partID) {
    try {
        // Connect to the database
        DB.connect();

        // Delete the part from the inventory table if it exists
        String deleteInventoryQuery = "DELETE FROM sys.inventory WHERE part_ID = " + partID;
        DB.update(deleteInventoryQuery);

        // Delete the part from the parts table
        String deletePartQuery = "DELETE FROM sys.parts WHERE part_ID = " + partID;
        DB.update(deletePartQuery);

        // Reload parts to keep the parts array updated
        reloadParts();

        // Returns the finished method.
        return true; 
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
    
}
    public void reloadParts() {
    try {
        
        //Get the total number of parts in the Database
        ResultSet countResult = DB.query("SELECT COUNT(*) FROM sys.parts;");
        countResult.next();
        int numItems = countResult.getInt(1);
        countResult.close();

        //Initialize the parts array with the same amount of indexes as stored in the databse
        parts = new Part[numItems];
        size = 0;

        //The SQL query to obtain the required data for the upcoming operations
        String query = "SELECT parts.part_id, parts.part_name, parts.price, parts.category, "
                     + "COALESCE(inventory.quantity, 0) AS quantity "
                     + "FROM sys.parts "
                     + "LEFT JOIN sys.inventory ON parts.part_id = inventory.part_id";

        // Execute the query
        ResultSet table = DB.query(query);

        //Loop through the result set and populate the parts array
        while (table.next()) {
            
            //Assigns the variables required to create the Part object.
            int partID = table.getInt("part_id");
            String partName = table.getString("part_name");
            int price = table.getInt("price");
            String category = table.getString("category");
            int quantity = table.getInt("quantity");
            
            //Creates the new part object
            Part p = new Part(partID, partName, price, category, quantity);

            //Stores the part object in the part's array.
            parts[size] = p;
            size++;
        }
        
        //Burns the house down, I mean shuts the SQL table
        table.close();

    } catch (SQLException ex) {
        Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    //A method used to update the part's details
    public boolean updatePartDetails(Part part) {
    try {
        
        //Connects to the Database
        DB.connect();

        //Update the parts table. Correctly updates it dynamically off the part enters detailed.
        String updatePartsQuery = "UPDATE sys.parts SET "
                + "part_name = '" + part.getName() + "', "
                + "category = '" + part.getCatogory() + "', "
                + "price = " + part.getPrice() + " "
                + "WHERE part_ID = " + part.getPartID();
        //Uses the Databse query to update the table.
        DB.update(updatePartsQuery);

        //Update the inventory table
        ResultSet rs = DB.query("SELECT quantity FROM sys.inventory WHERE part_ID = " + part.getPartID() + ";");
        
        
        if (rs.next()) {
            // Querys the databse and updates the tables to reflect the new change
           DB.update("UPDATE sys.inventory SET quantity = " + part.getQuantity() + " WHERE part_ID = " + part.getPartID() + ";");

        } 
        
        //If the part does not exist, then make a new part.
        else if (part.getQuantity() > 0) {
            // Part does not exist in inventory, insert it
            
            DB.update("INSERT INTO sys.parts (part_ID, part_name, price, category) VALUES ("+part.getPartID() + ",\"" + part.getName() + "\"," + part.getPrice() + ",\"" + part.getCatogory() + "\");");
            DB.update( "INSERT INTO sys.inventory (part_ID, quantity) VALUES (" + part.getPartID() + ", " + part.getQuantity() + ")");
        }
        
        //Table has left the building. aka it closes the table
        rs.close();

        //Reload parts array to reflect changes
        reloadParts();

        return true;
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}


}



