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
public class InTM {
    String ID;
    int Amount;
    double Price;
    String InputID;
    String IDProduct;
    

    public InTM() {
    }

    public InTM(String ID, int Amount, double Price, String InputID, String IDProduct) {
        this.ID = ID;
        this.Amount = Amount;
        this.Price = Price;
        this.InputID = InputID;
        this.IDProduct = IDProduct;
    }

    
    
    
    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

   
    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }

    public String getInputID() {
        return InputID;
    }

    public void setInputID(String InputID) {
        this.InputID = InputID;
    }
    
    
}
