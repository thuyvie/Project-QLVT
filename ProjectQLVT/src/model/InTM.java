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
    String Inputid;
    String DateInvoice;
    String ID;
    int Amount;
    double Price;
    double Total;
    String InputID;
    String IDProduct;
    String IDWare;
    String ProductID;
    int Inventory;
    int Amountinput;
    String Dateinput;
    Double OriginalPrice;
    String InputId;

    public InTM() {
    }

    public InTM(String Inputid, String DateInvoice, double Total) {
        this.Inputid = Inputid;
        this.DateInvoice = DateInvoice;
        this.Total = Total;
    }
    
    public InTM(String IDProduct,double Price,int Amount,double Total) {
        this.IDProduct = IDProduct;
        this.Price = Price;
        this.Amount = Amount;
        this.Total = Total;
    }
    
    public InTM(String ID, int Amount, double Price, String InputID, String IDProduct) {
        this.ID = ID;
        this.Amount = Amount;
        this.Price = Price;
        this.InputID = InputID;
        this.IDProduct = IDProduct;
    }
    
    public InTM(String ID, int Amount, double Price, String InputID, String IDProduct, String IDWare, String ProductID, int Inventory, int Amountinput, String Dateinput, Double OriginalPrice, String InputId) {
        this.ID = ID;
        this.Amount = Amount;
        this.Price = Price;
        this.InputID = InputID;
        this.IDProduct = IDProduct;
        this.IDWare = IDWare;
        this.ProductID = ProductID;
        this.Inventory = Inventory;
        this.Amountinput = Amountinput;
        this.Dateinput = Dateinput;
        this.OriginalPrice = OriginalPrice;
        this.InputId = InputId;
    }

    public InTM(String IDWare, String ProductID, int Inventory, int Amountinput, String Dateinput, Double OriginalPrice, String InputId) {
        this.IDWare = IDWare;
        this.ProductID = ProductID;
        this.Inventory = Inventory;
        this.Amountinput = Amountinput;
        this.Dateinput = Dateinput;
        this.OriginalPrice = OriginalPrice;
        this.InputId = InputId;
    }

    public String getIDWare() {
        return IDWare;
    }

    public void setIDWare(String IDWare) {
        this.IDWare = IDWare;
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

    public String getDateinput() {
        return Dateinput;
    }

    public void setDateinput(String Dateinput) {
        this.Dateinput = Dateinput;
    }

    public Double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(Double OriginalPrice) {
        this.OriginalPrice = OriginalPrice;
    }

    public String getInputId() {
        return InputId;
    }

    public void setInputId(String InputId) {
        this.InputId = InputId;
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

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    
    
}
