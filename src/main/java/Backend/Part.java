/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author darre
 */
public class Part {
    private int partID;
    private String name;
    private int price;
    private String catogory;

    public Part(int inPID, String inN, int inPrice, String inC) {
        this.partID = inPID;
        this.name = inN;
        this.price = inPrice;
        this.catogory = inC;
    }
    
    
}
