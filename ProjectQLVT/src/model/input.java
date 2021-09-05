/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Mun Chan
 */
public class input {
    String InputID; // id bang input
    String Date;
    Double Total;
    String ID; // id input detail
    private ArrayList<InTM> allInDetail;
    private ArrayList<InTM2> allInDetail2;
    int Amount;
    Double Price;
    String IDinput; // id input of input detail
    String IDProduct;
    String IDWare;// Id cua bang ware house
    String ProductID;
    int Inventory;
    int Amountinput;
    String Dateinput;
    Double OriginalPrice;
    String IDInput; // id in put bang ware house

    public input() {
    }

    public input(String InputID, String Date, Double Total) {
        this.InputID = InputID;
        this.Date = Date;
        this.Total = Total;
    }

    public input(String ID, int Amount, Double Price, String IDinput, String IDProduct) {
        this.ID = ID;
        this.Amount = Amount;
        this.Price = Price;
        this.IDinput = IDinput;
        this.IDProduct = IDProduct;
    }

    public input(String IDWare, String ProductID, int Inventory, int Amountinput, String Dateinput, Double OriginalPrice, String IDInput) {
        this.IDWare = IDWare;
        this.ProductID = ProductID;
        this.Inventory = Inventory;
        this.Amountinput = Amountinput;
        this.Dateinput = Dateinput;
        this.OriginalPrice = OriginalPrice;
        this.IDInput = IDInput;
    }

    public input( String IDProduct,Double Price,int Amount,Double Total) {
        this.Total = Total;
        this.Amount = Amount;
        this.Price = Price;
        this.IDProduct = IDProduct;
    }

    public input(String InputID, String Date, Double Total, int Amount, Double Price, String IDProduct, String ProductID, int Inventory, int Amountinput, String Dateinput, Double OriginalPrice) {
        this.InputID = InputID;
        this.Date = Date;
        this.Total = Total;
        this.Amount = Amount;
        this.Price = Price;
        this.IDProduct = IDProduct;
        this.ProductID = ProductID;
        this.Inventory = Inventory;
        this.Amountinput = Amountinput;
        this.Dateinput = Dateinput;
        this.OriginalPrice = OriginalPrice;
    }

    public input(String InputID, String Date, Double Total, int Amount, Double Price, String IDProduct, String ProductID, int Amountinput, String Dateinput, Double OriginalPrice) {
        this.InputID = InputID;
        this.Date = Date;
        this.Total = Total;
        this.Amount = Amount;
        this.Price = Price;
        this.IDProduct = IDProduct;
        this.ProductID = ProductID;
        this.Amountinput = Amountinput;
        this.Dateinput = Dateinput;
        this.OriginalPrice = OriginalPrice;
    }

    public input(String InputID, String Date, Double Total, String ID, ArrayList<InTM> allInDetail) {
        this.InputID = InputID;
        this.Date = Date;
        this.Total = Total;
        this.ID = ID;
        this.allInDetail = allInDetail;
    }

    public input(String InputID, String Date, Double Total, ArrayList<InTM> allInDetail) {
        this.InputID = InputID;
        this.Date = Date;
        this.Total = Total;
        this.allInDetail = allInDetail;
    }
    
    public ArrayList<InTM2> getAllInDetail2() {
        return allInDetail2;
    }

    public void setAllInDetail2(ArrayList<InTM2> allInDetail2) {
        this.allInDetail2 = allInDetail2;
    }
    
    public String getInputID() {
        return InputID;
    }

    public void setInputID(String InputID) {
        this.InputID = InputID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getIDinput() {
        return IDinput;
    }

    public void setIDinput(String IDinput) {
        this.IDinput = IDinput;
    }

    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
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

    

    public String getIDInput() {
        return IDInput;
    }

    public void setIDInput(String IDInput) {
        this.IDInput = IDInput;
    }

    public ArrayList<InTM> getAllInDetail() {
        return allInDetail;
    }

    public void setAllInDetail(ArrayList<InTM> allInDetail) {
        this.allInDetail = allInDetail;
    }
    
}
