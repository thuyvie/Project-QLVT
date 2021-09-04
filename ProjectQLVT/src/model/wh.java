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
public class wh {
    String ID;
    String ProductID;
    String namepro;
    int Inventory;
    int Amountinput;
    Double OriginalPrice;
    String Dateinput;
    String IDInput;

    public wh(String ID, String ProductID, int Inventory, int Amountinput, Double OriginalPrice, String IDInput) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.Inventory = Inventory;
        this.Amountinput = Amountinput;
        this.OriginalPrice = OriginalPrice;
        this.IDInput = IDInput;
    }

    public wh(String ID, String ProductID, int Inventory, int Amountinput,String Dateinput, Double OriginalPrice,  String IDInput) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.Inventory = Inventory;
        this.Amountinput = Amountinput;
        this.Dateinput = Dateinput;
        this.OriginalPrice = OriginalPrice;
        this.IDInput = IDInput;
    }

    public wh(String ID, String ProductID, String namepro, int Inventory, int Amountinput, String Dateinput, String IDInput) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.namepro = namepro;
        this.Inventory = Inventory;
        this.Amountinput = Amountinput;
        this.Dateinput = Dateinput;
        this.IDInput = IDInput;
    }

    public wh(String ID, String ProductID, String namepro, String Dateinput, String IDInput) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.namepro = namepro;
        this.Dateinput = Dateinput;
        this.IDInput = IDInput;
    }
    
    public wh() {
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
    
    public String getIDInput() {
        return IDInput;
    }

    public String getDateinput() {
        return Dateinput;
    }

    public void setDateinput(String Dateinput) {
        this.Dateinput = Dateinput;
    }
    
    public void setIDInput(String IDInput) {
        this.IDInput = IDInput;
    }

    public String getNamepro() {
        return namepro;
    }

    public void setNamepro(String namepro) {
        this.namepro = namepro;
    }
    
}
