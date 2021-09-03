/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author thanh
 */
public class warehouse {
    String ID,ProductID,DateInput;
    int Inventory,AmountInput;
    Double OriginalPrice;
     public warehouse(String ID, String ProductID, int Inventory, int AmountInput,Double OriginalPrice,String DateInput) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.Inventory = Inventory;
        this.AmountInput = AmountInput;
        this.OriginalPrice = OriginalPrice;
        this.DateInput = DateInput;       
    }
     public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
     public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    } 
    public String getDateInput() {
        return DateInput;
    }

    public void setDateInput(String DateInput) {
        this.DateInput = DateInput;
    }
    public int getInventory() {
        return Inventory;
    }

    public void setInventory(int Inventory) {
        this.Inventory = Inventory;
    }
    public int getAmountInput() {
        return AmountInput;
    }

    public void setAmountInput(int AmountInput) {
        this.AmountInput = AmountInput;
    }
    public Double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(Double OriginalPrice) {
        this.OriginalPrice = OriginalPrice;
    }
    
}
