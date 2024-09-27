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
    private int partID;
    private String name;
    private int price;
    private String catogory;
    private int quantity;

    public Part(int inPID, String inN, int inPrice, String inC, int inQty) {
        this.partID = inPID;
        this.name = inN;
        this.price = inPrice;
        this.catogory = inC;
        this.quantity = inQty;
    }
    
    public boolean updateQuantity(int updateQty){
    //Calculate the new quantity
    int newQuantity = quantity + updateQty;

    //Ensure quantity does not go below zero
    if (newQuantity < 0) {
        System.err.println("Cannot reduce quantity below zero.");
        return false;
    }

    quantity = newQuantity;

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

        rs.close();

    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(Part.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }

    return true;
    }

    public int getPartID() {
        return partID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCatogory() {
        return catogory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCatogory(String catogory) {
        this.catogory = catogory;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    

        
    
}
