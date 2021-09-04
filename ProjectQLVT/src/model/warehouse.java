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
    String itemCode;
    private product pro;
    String Inventory,AmountInput;
    int inventory1;
    int Amountinput;
    Double OriginalPrice;
    String IDInput;
     public warehouse(String ID, String ProductID,  String Inventory,  String AmountInput,Double OriginalPrice,String DateInput) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.Inventory = Inventory;
        this.AmountInput = AmountInput;
        this.OriginalPrice = OriginalPrice;
        this.DateInput = DateInput;       
    }

    public warehouse() {
    }
     
    public warehouse(String ID, String ProductID, String DateInput, product pro, String Inventory, String AmountInput, Double OriginalPrice) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.DateInput = DateInput;
        this.pro = pro;
        this.Inventory = Inventory;
        this.AmountInput = AmountInput;
        this.OriginalPrice = OriginalPrice;
    }

    public warehouse(String ID, String ProductID, String DateInput, String itemCode, product pro, String Inventory, String AmountInput, Double OriginalPrice) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.DateInput = DateInput;
        this.itemCode = itemCode;
        this.pro = pro;
        this.Inventory = Inventory;
        this.AmountInput = AmountInput;
        this.OriginalPrice = OriginalPrice;
    }

    public warehouse(String ID, String ProductID, String DateInput, String Inventory, String AmountInput, Double OriginalPrice, String IDInput) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.DateInput = DateInput;
        this.Inventory = Inventory;
        this.AmountInput = AmountInput;
        this.OriginalPrice = OriginalPrice;
        this.IDInput = IDInput;
    }

    public warehouse(String ID, String ProductID, String DateInput, int inventory1, int Amountinput, Double OriginalPrice, String IDInput) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.DateInput = DateInput;
        this.inventory1 = inventory1;
        this.Amountinput = Amountinput;
        this.OriginalPrice = OriginalPrice;
        this.IDInput = IDInput;
    }

    public int getInventory1() {
        return inventory1;
    }

    public void setInventory1(int inventory1) {
        this.inventory1 = inventory1;
    }

    public int getAmountinput() {
        return Amountinput;
    }

    public void setAmountinput(int Amountinput) {
        this.Amountinput = Amountinput;
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

    public String getInventory() {
        return Inventory;
    }

    public void setInventory(String Inventory) {
        this.Inventory = Inventory;
    }

    public String getAmountInput() {
        return AmountInput;
    }

    public void setAmountInput(String AmountInput) {
        this.AmountInput = AmountInput;
    }
   
    public Double getOriginalPrice() {
        return OriginalPrice;
    }

    public void setOriginalPrice(Double OriginalPrice) {
        this.OriginalPrice = OriginalPrice;
    }

    public product getPro() {
        return pro;
    }

    public void setPro(product pro) {
        this.pro = pro;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getIDInput() {
        return IDInput;
    }

    public void setIDInput(String IDInput) {
        this.IDInput = IDInput;
    }
}
