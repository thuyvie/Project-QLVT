/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Mun Chan
 */
public class InTM2 {
    String ID;
    String ProductID;
    int Inventory;
    int Amountinput;
    String Dateinput;
    Double OriginalPrice;
    String InputID;
    public InTM2(String ID, String ProductID, int Inventory, int Amountinput, String Dateinput, Double OriginalPrice, String InputID) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.Inventory = Inventory;
        this.Amountinput = Amountinput;
        this.Dateinput = Dateinput;
        this.OriginalPrice = OriginalPrice;
        this.InputID = InputID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDateinput() {
        return Dateinput;
    }

    public void setDateinput(String Dateinput) {
        this.Dateinput = Dateinput;
    }

    public String getInputID() {
        return InputID;
    }

    public void setInputID(String InputID) {
        this.InputID = InputID;
    }

    
    
    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public int getInventory() {
        return Inventory;
    }

    public void setInventory(int Inventory) {
        this.Inventory = Inventory;
    }

    public int getAmountinput() {
        return Amountinput;
    }

    public void setAmountinput(int Amountinput) {
        this.Amountinput = Amountinput;
    }

    public Double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(Double OriginalPrice) {
        this.OriginalPrice = OriginalPrice;
    }
    
}
