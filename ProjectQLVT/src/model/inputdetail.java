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
public class inputdetail {
    String ID;
    
    int Amount;
    Double Price;
    String InputID;
    String IDProduct;

    public inputdetail() {
    }

    public inputdetail(String ID, int Amount, Double Price, String InputID, String IDProduct) {
        this.ID = ID;
        this.Amount = Amount;
        this.Price = Price;
        this.InputID = InputID;
        this.IDProduct = IDProduct;
    }

    
    

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getInputID() {
        return InputID;
    }

    public void setInputID(String InputID) {
        this.InputID = InputID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    
    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }
    
}
