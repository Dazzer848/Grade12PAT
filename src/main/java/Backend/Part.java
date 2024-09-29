/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import DBMS.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darre
 */
public class Part {
    //Initializes the variables
    private int partID;
    private String name;
    private int price;
    private String catogory;
    private int quantity;

    //Constructor!
    public Part(int inPID, String inN, int inPrice, String inC, int inQty) {
        this.partID = inPID;
        this.name = inN;
        this.price = inPrice;
        this.catogory = inC;
        this.quantity = inQty;
    }
    
    //A method which updates the quantity of things
    public boolean updateQuantity(int updateQty){
        
    //Calculate the new quantity
    int newQuantity = quantity + updateQty;

    //Ensure quantity does not go below zero
    if (newQuantity < 0) {
        System.err.println("Cannot reduce quantity below zero.");
        return false;
    }

    //set's the part's quantity to the now updated quantity
    quantity = newQuantity;

    //The query to send the new part quanityt to the databse
    try {
        DB.connect();

        //Check if the part exists in the inventory
        String checkQuery = "SELECT quantity FROM sys.inventory WHERE part_ID = " + partID;
        ResultSet rs = DB.query(checkQuery);

        if (rs.next()) {
            if (quantity > 0) {
                //Part exists, update the quantity
                String updateQuery = "UPDATE sys.inventory SET quantity = " + quantity + " WHERE part_ID = " + partID;
                DB.update(updateQuery);
            } else {
                // If the qty is zero delete the item from the table
                String deleteQuery = "DELETE FROM sys.inventory WHERE part_ID = " + partID;
                DB.update(deleteQuery);
            }
        } else {
            // Part doesn't exist, insert it if quantity is greater than zero
            if (quantity > 0) {
                String insertQuery = "INSERT INTO sys.inventory (part_ID, quantity) VALUES (" + partID + ", " + quantity + ")";
                DB.update(insertQuery);
            } else {
                System.err.println("Cannot insert part with zero quantity.");
                return false;
            }
        }
        
        //shuts the table
        rs.close();

    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(Part.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
    
    //returns the methods completion.
    return true;
    }

    //A method which returns the partID of the part
    public int getPartID() {
        return partID;
    }

    //A method which returns a strng representation of a String
    public String getName() {
        return name;
    }

    //A method which returns the price of a part
    public int getPrice() {
        return price;
    }

    //A method whcih returns the catogory of the part
    public String getCatogory() {
        return catogory;
    }

    //A method which returns the quantity of the parts in the inventory
    public int getQuantity() {
        return quantity;
    }

    //A method which sets the partID
    public void setPartID(int partID) {
        this.partID = partID;
    }

    //A method which updates the part Name
    public void setName(String name) {
        this.name = name;
    }
    
    //A method which set's the price of a part
    public void setPrice(int price) {
        this.price = price;
    }

    //A method which set's the catorgory of the part
    public void setCatogory(String catogory) {
        this.catogory = catogory;
    }

    //a method which set's the Quantity of the part
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    //A method used during sales, which print's out a toString of the Parts in the sale
    public String toStringForSale() {
        return "\nPART ID: " + partID + "\nPART NAME: " + name + "\nPRICE: " + price;
    }
   
    
    

        
    
}
