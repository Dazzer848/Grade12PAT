/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author darre
 */
public class Sale {
    
    //The global variables of this class
    private ArrayList<Part> sales = new ArrayList<Part>();
    private int total;
    private ArrayList<Integer> quantities =  new ArrayList<Integer>();
    private int clientId;
    private int saleID;

    //Constructor?
    public Sale(ArrayList<Part> inParts, int inTotal, ArrayList<Integer> inQuantities, int inCID, int inSID) {
        this.sales = inParts;
        this.total = inTotal;
        this.quantities = inQuantities;
        this.clientId = inCID;
        this.saleID = inSID;
    }

    //Returns the list of parts involved in the sale
    public ArrayList<Part> getSales() {
        return sales;
    }

    //A method which returns the total of the sale
    public int getTotal() {
        return total;
    }
    
    
    //A method which returns the quantities of each item sold in the sale
    public ArrayList<Integer> getQuantities() {
        return quantities;
    }
    
    //A string represtation of the sale
    public String toString(){
        String output = "============== ITEMS BOUGHT ==================\n";
        
        //Loops through all the sales in the array
        for(int i = 0; i < sales.size(); i++){
            //Logical item number simply reffers to item 1 at index 0
            int logicalItemNumber = i + 1;
            output += "================== ITEM " + logicalItemNumber + " =====================";
            Part p = sales.get(i);
            output += p.toStringForSale() + "\n";
            output += "QUANTITY: " + quantities.get(i) + "\n\n";
            
        }
        output += "TOTAL: " + total;
        return output;
        
    }

    //Returs the ClientID that the sale belogns too 
    public int getClientId() {
        return clientId;
    }

    //Returns the Sale ID
    public int getSaleID() {
        return saleID;
    }
    
    
    
    
}
