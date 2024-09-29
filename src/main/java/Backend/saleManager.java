/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import DBMS.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darre
 */
public class saleManager {
    private Sale[] salesArr;
    private inventoryManager manager = new inventoryManager();
    private int size;
    
    
    // Problem here is how do I store a unique quantity for each item purchased in the array?
    
    public saleManager() {

        try {
            size = 0;
            
            
            // Connect to the Database
            DB.connect();
            
            
            
            // Get the total number of parts
            ResultSet highestSaleIdTable = DB.query("SELECT sale_ID FROM sys.sales group by sale_ID order by sale_ID DESC;");
            highestSaleIdTable.next();
            int highestSaleID = highestSaleIdTable.getInt(1);
            salesArr = new Sale[highestSaleID];
            
            for(int i = 1; i <= highestSaleID;i++){
                ResultSet numItemsInSaleTable = DB.query("SELECT count(sale_ID) FROM sys.sales where sale_ID = " + i + ";");
                numItemsInSaleTable.next();
                int numItemsInSale = numItemsInSaleTable.getInt(1);
                
                ArrayList<Part> partsInSale = new ArrayList<Part>();
                ArrayList<Integer> quantitiesOfPart = new ArrayList<Integer>();
                
                for(int f = 1; f <= numItemsInSale; f++){
                    ResultSet saleTable = DB.query("SELECT * FROM sys.sales where sale_ID = " + f + ";");
                    saleTable.next();
                    int saleID = saleTable.getInt("sale_ID");
                    int clientID = saleTable.getInt(2);
                    int partID = saleTable.getInt(3);
                    int quantity = saleTable.getInt(4);
                    
                    quantitiesOfPart.add(quantity);
                    
                    Part p = manager.findPartByID(partID);
                    partsInSale.add(p);
                    
                    
                }
                int total = 0;
                for(int j = 0; j < numItemsInSale; j++){
                    total += partsInSale.get(j).getPrice();
                }
                
                Sale s = new Sale(partsInSale, total, quantitiesOfPart);
                salesArr[size] = s;  
                size++;
            }
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(saleManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(saleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String toString(int inSID) {
        Sale s = salesArr[inSID];
        return s.toString();
        
    }
    
    
    public void createSale(ArrayList<Part> inParts, int inTotal, int clientID){
        try {
            DB.connect();
            
            //Dealing with the inventory
            for(int i = 0; i < inParts.size();i++){
                Part p = inParts.get(i);
                
                
                ResultSet currentQuantityTable = DB.query("SELECT Quantity FROM sys.inventory WHERE part_ID = " + p.getPartID() + ";");
                currentQuantityTable.next();
                int currentQuantity = currentQuantityTable.getInt(1);
                
                int newQuantity = -p.getQuantity();
                manager.updatePartQty(p.getPartID(), newQuantity);
            }
            
            //Creating sale entry
            ResultSet highestSaleIDTABLE = DB.query("SELECT sale_ID FROM sys.sales order by sale_ID DESC LIMIT 1;");
            highestSaleIDTABLE.next();
            int highestSaleID = highestSaleIDTABLE.getInt(1);
            int newSaleID = highestSaleID + 1;
            
            for(int i = 0; i < inParts.size(); i++){
                Part p = inParts.get(i);
                int quantity = p.getQuantity();
                DB.update("INSERT INTO `sys`.`sales` (`sale_ID`, `ClientID`, `PartID`, `Quantity`) VALUES (" + newSaleID + "," + clientID + "," + p.getPartID() +"," + quantity + ");");
            }
            
            System.out.println("Your sale was created");

            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(saleManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(saleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
}
