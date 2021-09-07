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
public class dtmTM {
    String ID;
    String OutputID;
    String WareHouseID;
    private wh out;
    String code;
    String name;
    String price;
    String QTY;
//    String Total;
    double Price;
    double qty;
    Double Total;
    public dtmTM(String code, String name, String price, String QTY) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.QTY = QTY;
    }

    public dtmTM(String code, String name, String price, String QTY, Double Total) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.QTY = QTY;
        this.Total = Total;
    }

    public dtmTM(String ID, String OutputID, String WareHouseID, String code, String price, String QTY) {
        this.ID = ID;
        this.OutputID = OutputID;
        this.WareHouseID = WareHouseID;
        this.code = code;
        this.price = price;
        this.QTY = QTY;
    }

    public dtmTM(String code, String name, double Price, double qty, Double Total) {
        this.code = code;
        this.name = name;
        this.Price = Price;
        this.qty = qty;
        this.Total = Total;
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
    
   

    public wh getOut() {
        return out;
    }

    public void setOut(wh out) {
        this.out = out;
    }
  

    public dtmTM() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    

    
}
