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
public class orderdetail {
    String OrderID;
    private order2 ordid;
    private ord oid;
    String IDProduct;
    private product proid;
    String Amount;
    String Price;

    public orderdetail() {
    }

    public orderdetail(String OrderID, order2 ordid, ord oid, String IDProduct, product proid, String Amount, String Price) {
        this.OrderID = OrderID;
        this.ordid = ordid;
        this.oid = oid;
        this.IDProduct = IDProduct;
        this.proid = proid;
        this.Amount = Amount;
        this.Price = Price;
    }

    public orderdetail(String OrderID, String IDProduct, String Amount, String Price) {
        this.OrderID = OrderID;
        this.IDProduct = IDProduct;
        this.Amount = Amount;
        this.Price = Price;
    }

    

    public ord getOid() {
        return oid;
    }

    public void setOid(ord oid) {
        this.oid = oid;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

   
    
    

    public String getIDProduct() {
        return IDProduct;
    }

    public void setIDProduct(String IDProduct) {
        this.IDProduct = IDProduct;
    }

    public order2 getOrdid() {
        return ordid;
    }

    public void setOrdid(order2 ordid) {
        this.ordid = ordid;
    }

    

    

    public product getProid() {
        return proid;
    }

    public void setProid(product proid) {
        this.proid = proid;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }

   
}
