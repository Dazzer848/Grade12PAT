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
        // Connect to the Database
        DB.connect();

        // Get the total number of parts
        ResultSet countResult = DB.query("SELECT COUNT(*) FROM sys.parts;");
        countResult.next();
        int numItems = countResult.getInt(1);
        countResult.close();

        // Initialize the parts array
        parts = new Part[numItems];
        size = 0;

        // Define the query with LEFT JOIN to include quantity
        String query = "SELECT parts.part_id, parts.part_name, parts.price, parts.category, "
                     + "COALESCE(inventory.quantity, 0) AS quantity "
                     + "FROM sys.parts "
                     + "LEFT JOIN sys.inventory ON parts.part_id = inventory.part_id";

        // Execute the query
        ResultSet table = DB.query(query);

        // Loop through the result set and populate the parts array
        while (table.next()) {
            int partID = table.getInt("part_id");
            String partName = table.getString("part_name");
            int price = table.getInt("price");
            String catogory = table.getString("category");
            int quantity = table.getInt("quantity");

            Part p = new Part(partID, partName, price, catogory, quantity);

            parts[size] = p;
            size++;
        }

        table.close();

    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
    
    public boolean updatePart(int inPID, int inQty){
    Part p = findPartByID(inPID);
    if (p != null) {
        return p.updateQuantity(inQty);
    } else {
        System.err.println("Part with ID " + inPID + " not found in inventoryManager.");
        return false;
    }
    }
    
    
    
  public Part findPartByID(int partID) {
    for (int i = 0; i < size; i++) {
        if (parts[i].getPartID() == partID) {
            return parts[i];
        }
    }
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

        return true; 
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
    
}
    public void reloadParts() {
    try {
        // Get the total number of parts
        ResultSet countResult = DB.query("SELECT COUNT(*) FROM sys.parts;");
        countResult.next();
        int numItems = countResult.getInt(1);
        countResult.close();

        // Initialize the parts array
        parts = new Part[numItems];
        size = 0;

        // Define the query with LEFT JOIN to include quantity
        String query = "SELECT parts.part_id, parts.part_name, parts.price, parts.category, "
                     + "COALESCE(inventory.quantity, 0) AS quantity "
                     + "FROM sys.parts "
                     + "LEFT JOIN sys.inventory ON parts.part_id = inventory.part_id";

        // Execute the query
        ResultSet table = DB.query(query);

        // Loop through the result set and populate the parts array
        while (table.next()) {
            int partID = table.getInt("part_id");
            String partName = table.getString("part_name");
            int price = table.getInt("price");
            String category = table.getString("category");
            int quantity = table.getInt("quantity");

            Part p = new Part(partID, partName, price, category, quantity);

            parts[size] = p;
            size++;
        }

        table.close();

    } catch (SQLException ex) {
        Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public boolean updatePartDetails(Part part) {
    try {
        DB.connect();

        // Update the parts table
        String updatePartsQuery = "UPDATE sys.parts SET "
                + "part_name = '" + part.getName() + "', "
                + "category = '" + part.getCatogory() + "', "
                + "price = " + part.getPrice() + " "
                + "WHERE part_ID = " + part.getPartID();
        DB.update(updatePartsQuery);

        // Update the inventory table
        String checkInventoryQuery = "SELECT quantity FROM sys.inventory WHERE part_ID = " + part.getPartID();
        ResultSet rs = DB.query(checkInventoryQuery);

        if (rs.next()) {
            // Part exists in inventory, update quantity
            String updateInventoryQuery = "UPDATE sys.inventory SET quantity = " + part.getQuantity()
                    + " WHERE part_ID = " + part.getPartID();
            DB.update(updateInventoryQuery);
        } else if (part.getQuantity() > 0) {
            // Part does not exist in inventory, insert it
            String insertInventoryQuery = "INSERT INTO sys.inventory (part_ID, quantity) VALUES ("
                    + part.getPartID() + ", " + part.getQuantity() + ")";
            DB.update(insertInventoryQuery);
        }
        rs.close();

        // Reload parts array to reflect changes
        reloadParts();

        return true;
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(inventoryManager.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}


}



