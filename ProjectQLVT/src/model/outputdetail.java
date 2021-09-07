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
public class outputdetail {
    String ID;
    String OutputID;
    String WareHouseID;
    String Amountoutput;
    String Price;
    String ProductID;

    public outputdetail(String ID, String OutputID, String WareHouseID, String Amountoutput, String Price, String ProductID) {
        this.ID = ID;
        this.OutputID = OutputID;
        this.WareHouseID = WareHouseID;
        this.Amountoutput = Amountoutput;
        this.Price = Price;
        this.ProductID = ProductID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOutputID() {
        return OutputID;
    }

    public void setOutputID(String OutputID) {
        this.OutputID = OutputID;
    }

    public String getWareHouseID() {
        return WareHouseID;
    }

    public void setWareHouseID(String WareHouseID) {
        this.WareHouseID = WareHouseID;
    }

    public String getAmountoutput() {
        return Amountoutput;
    }

    public void setAmountoutput(String Amountoutput) {
        this.Amountoutput = Amountoutput;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }
    
}
