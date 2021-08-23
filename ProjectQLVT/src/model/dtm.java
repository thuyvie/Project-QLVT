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
public class dtm {
    String namecus;
    String paymentform;
    String paid;
    String owe;
    String TotalDebt;
    String amount;

    public dtm(String paymentform, String paid, String owe, String TotalDebt) {
        this.paymentform = paymentform;
        this.paid = paid;
        this.owe = owe;
        this.TotalDebt = TotalDebt;
    }

    public dtm(String namecus, String paymentform, String paid, String owe, String TotalDebt) {
        this.namecus = namecus;
        this.paymentform = paymentform;
        this.paid = paid;
        this.owe = owe;
        this.TotalDebt = TotalDebt;
    }

    public dtm(String namecus, String paymentform, String paid, String owe, String TotalDebt, String amount) {
        this.namecus = namecus;
        this.paymentform = paymentform;
        this.paid = paid;
        this.owe = owe;
        this.TotalDebt = TotalDebt;
        this.amount = amount;
    }
    
    public String getNamecus() {
        return namecus;
    }

    public void setNamecus(String namecus) {
        this.namecus = namecus;
    }

    public String getPaymentform() {
        return paymentform;
    }

    public void setPaymentform(String paymentform) {
        this.paymentform = paymentform;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getOwe() {
        return owe;
    }

    public void setOwe(String owe) {
        this.owe = owe;
    }

    public String getTotalDebt() {
        return TotalDebt;
    }

    public void setTotalDebt(String TotalDebt) {
        this.TotalDebt = TotalDebt;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    
}
