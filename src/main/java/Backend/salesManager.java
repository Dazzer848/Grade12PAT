/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import DBMS.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author darre
 */
public class salesManager {

    private inventoryManager manager;
    private int size = 0;
    private Sale[] sales;

    public salesManager() {
        try {
            // Connect to the Database
            DB.connect();

            // Get the total number of parts
            ResultSet countResult = DB.query("SELECT COUNT(*) FROM clientSales;");
            countResult.next();
            int numItems = countResult.getInt(1);
            countResult.close();

            // Initialize the parts array
            sales = new Sale[numItems];
            size = 0;

            // Define the query with LEFT JOIN to include quantity
            String query = "SELECT * FROM clientSales;";

            // Execute the query
            ResultSet saleDetails = DB.query(query);
            ResultSet saleContents = DB.query("SELECT * FROM sys.sales");
            saleContents.next();

            // Loop through the result set and populate the parts array
            while (saleDetails.next()) {
                int clientID = saleDetails.getInt("client_ID");
                int saleID = saleDetails.getInt("Sale_ID");
                int Total = saleDetails.getInt("Total");

                int item1ID = saleContents.getInt("Item_1_ID");
                int item1Qty = saleContents.getInt("Item_1_Qty");

                itemSold item1 = new itemSold(item1ID, item1Qty);

                int item2ID = saleContents.getInt("Item_2_ID");
                int item2Qty = saleContents.getInt("Item_2_QTY");

                itemSold item2 = new itemSold(item2ID, item2Qty);

                int item3ID = saleContents.getInt("Item_3_ID");
                int item3Qty = saleContents.getInt("Item_3_Qty");

                itemSold item3 = new itemSold(item3ID, item3Qty);

                int item4ID = saleContents.getInt("Item_4_ID");
                int item4Qty = saleContents.getInt("Item_4_Qty");

                itemSold item4 = new itemSold(item4ID, item4Qty);

                int item5ID = saleContents.getInt("Item_5_ID");
                int item5Qty = saleContents.getInt("Item_5_Qty");

                itemSold item5 = new itemSold(item5ID, item5Qty);

                itemSold[] arr = new itemSold[5];
                arr[0] = item1;
                arr[1] = item2;
                arr[2] = item3;
                arr[3] = item4;
                arr[4] = item5;

                Sale p = new Sale(clientID, saleID, Total, arr);

                sales[size] = p;
                size++;
            }

            saleContents.close();
            saleDetails.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(salesManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(salesManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void newSale(int item1ID, int Item1QtyBought, int item2ID, int item2QtyBought, int item3ID, int item3Qty, int item4ID, int item4Qty, int item5Id, int item5QtyBought, int clientID) {
        if (item1ID != 0) {
            manager.updatePart(item1ID, -Item1QtyBought);
        }
        if (item2ID != 0) {
            manager.updatePart(item2ID, -item2QtyBought);
        }
        if (item3ID != 0) {
            manager.updatePart(item3ID, -item3Qty);
        }
        if (item4ID != 0) {
            manager.updatePart(item4ID, -item4Qty);
        }
        if (item5Id != 0) {
            manager.updatePart(item5Id, -item5QtyBought);
        }

    }
}
