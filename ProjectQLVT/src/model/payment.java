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
public class payment {
    String payid;
    String NameCus;
    Double amount;
    String OrdID;

    public payment(String payid, String NameCus, Double amount, String OrdID) {
        this.payid = payid;
        this.NameCus = NameCus;
        this.amount = amount;
        this.OrdID = OrdID;
    }
    
    public payment() {
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getNameCus() {
        return NameCus;
    }

    public void setNameCus(String NameCus) {
        this.NameCus = NameCus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrdID() {
        return OrdID;
    }

    public void setOrdID(String OrdID) {
        this.OrdID = OrdID;
    }

   

   
    
}
