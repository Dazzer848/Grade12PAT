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
    //Global variabels of the class.

    // Sales array is a storage method to store all the sales that have been made.
    private Sale[] salesArr;

    // Creates an inventory manager object to manager the quantitis of the items in the database 
    private inventoryManager manager = new inventoryManager();

    //Varibale which keeps track of the size of the array
    private int size;

    public saleManager() {

        try {
            //Set's the size of the salesArray to zero
            size = 0;

            //Connect to the database
            DB.connect();

            //Get the total number of parts
            ResultSet highestSaleIdTable = DB.query("SELECT sale_ID FROM sys.sales group by sale_ID order by sale_ID DESC;");
            highestSaleIdTable.next();
            int highestSaleID = highestSaleIdTable.getInt(1);

            // Set's the sales array to the max amount of components stored
            salesArr = new Sale[highestSaleID];

            //This loop gathers all the details of the parts used in EACH sale
            for (int i = 1; i <= highestSaleID; i++) {

                //Get's the number of items in the sale
                ResultSet numItemsInSaleTable = DB.query("SELECT count(sale_ID) FROM sys.sales where sale_ID = " + i + ";");
                numItemsInSaleTable.next();
                int numItemsInSale = numItemsInSaleTable.getInt(1);

                //Creates two ArrayLists to store the parts sold and the quanitities of each part sold in the transactions
                ArrayList<Part> partsInSale = new ArrayList<Part>();
                ArrayList<Integer> quantitiesOfPart = new ArrayList<Integer>();

                //Creating the clientID which will be used in multiple operations
                int clientID = 0;

                // Get's the parts of the sale in question and then assigns them to the object
                for (int f = 1; f <= numItemsInSale; f++) {
                    ResultSet saleTable = DB.query("SELECT * FROM sys.sales where sale_ID = " + f + ";");
                    saleTable.next();

                    // Assigns the values to object
                    int saleID = saleTable.getInt("sale_ID");
                    clientID = saleTable.getInt(2);
                    int partID = saleTable.getInt(3);
                    int quantity = saleTable.getInt(4);

                    //Adds the amount of that part purchased to the quantity array
                    quantitiesOfPart.add(quantity);

                    //Adds the part object to the sale
                    Part p = manager.findPartByID(partID);

                    // Adds the part that was purchased to the ArrayList 
                    partsInSale.add(p);

                }

                //Clulates the total of the sale
                int total = 0;
                for (int j = 0; j < numItemsInSale; j++) {
                    //Loops through the parts registed as a paet of the sale and then adds their prices. 
                    total += partsInSale.get(j).getPrice() * quantitiesOfPart.get(j);
                }

                ResultSet loytalyTable = DB.query("SELECT Loyalty FROM sys.clients WHERE ClientID = " + clientID + ";");
                loytalyTable.next();
                boolean loyalty = loytalyTable.getBoolean("Loyalty");

                if (loyalty == true) {
                    total = (int) (total - (total * 0.15));
                }

                //Registers the new sale
                Sale s = new Sale(partsInSale, total, quantitiesOfPart);
                //Adds the sale to the array
                salesArr[size] = s;
                size++;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(saleManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(saleManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //A to string method or the sales
    public String toString(int inSID) {
        Sale s = salesArr[inSID];
        return s.toString();

    }

    // A method whcih creates a sale obejct and adds it to the list
    public void createSale(ArrayList<Part> inParts, int inTotal, int clientID) {
        try {
            //Establishes connection to the Database
            DB.connect();

            //Dealing with the inventory
            for (int i = 0; i < inParts.size(); i++) {
                Part p = inParts.get(i);

                //Get's the current amount of part's in the inventory
                ResultSet currentQuantityTable = DB.query("SELECT Quantity FROM sys.inventory WHERE part_ID = " + p.getPartID() + ";");
                currentQuantityTable.next();
                // Stores the current amount of this specific part in the inventory
                int currentQuantity = currentQuantityTable.getInt(1);

                //Assigns the new quanity. It is a negative as the quantiyt stored in the sale object is negative, but we want to reduce the quantity of the inventory to reflect the part leaving the shop
                int newQuantity = -p.getQuantity();

                //Updates the inventory using the inventoryManager object
                manager.updatePartQty(p.getPartID(), newQuantity);
            }

            //Getting the current highest saleID
            ResultSet highestSaleIDTABLE = DB.query("SELECT sale_ID FROM sys.sales order by sale_ID DESC LIMIT 1;");
            highestSaleIDTABLE.next();
            int highestSaleID = highestSaleIDTABLE.getInt(1);

            //Creating   the next saleID to be used as a unique identifier for this transaction
            int newSaleID = highestSaleID + 1;

            int total = 0;

            // Loops through the parts in the sale and buidls the query for the object
            for (int i = 0; i < inParts.size(); i++) {
                Part p = inParts.get(i);
                int quantity = p.getQuantity();

                //Insert's the sale for this specific item into the sale table
                DB.update("INSERT INTO `sys`.`sales` (`sale_ID`, `ClientID`, `PartID`, `Quantity`) VALUES (" + newSaleID + "," + clientID + "," + p.getPartID() + "," + quantity + ");");
                total += p.getPrice() * quantity;
            }

            ResultSet loytalyTable = DB.query("SELECT Loyalty FROM sys.clients WHERE ClientID = " + clientID + ";");
            loytalyTable.next();
            boolean loyalty = loytalyTable.getBoolean("Loyalty");

            if (loyalty == true) {
                total = (int) (total - (total * 0.15));
            }

               DB.update("UPDATE sys.clients SET TotalSpent = TotalSpent + " + total + " WHERE ClientID = " + clientID + ";");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(saleManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(saleManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
