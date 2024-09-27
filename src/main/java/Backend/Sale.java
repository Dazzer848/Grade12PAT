/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author darre
 */
public class Sale {
    private int SaleID;
    private int clientID;
    private int Total;
    private itemSold[] itemsBought;

    public Sale(int SaleID, int clientID, int Total, itemSold[] inItemsBought) {
        this.SaleID = SaleID;
        this.clientID = clientID;
        this.Total = Total;
        this.itemsBought = inItemsBought;
    }

    public void setSaleID(int SaleID) {
        this.SaleID = SaleID;
    }

    public int getSaleID() {
        return SaleID;
    }
    

    public int getClientID() {
        return clientID;
    }

    public int getTotal() {
        return Total;
    }


    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }
    
    
}
