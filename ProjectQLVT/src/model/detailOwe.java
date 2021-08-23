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
public class detailOwe {
    String ID;
    String NameCus;
    String PhoneCus;
    Double Paid;
    Double Owe;
    Double TotalDebt;
    String IdOwe;

    public detailOwe(String ID, String NameCus, String PhoneCus, Double Paid, Double Owe, Double TotalDebt, String IdOwe) {
        this.ID = ID;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.Paid = Paid;
        this.Owe = Owe;
        this.TotalDebt = TotalDebt;
        this.IdOwe = IdOwe;
    }

    public detailOwe(String ID,Double Paid, Double Owe, Double TotalDebt, String IdOwe) {
        this.ID = ID;
        this.Paid = Paid;
        this.Owe = Owe;
        this.TotalDebt = TotalDebt;
        this.IdOwe = IdOwe;
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNameCus() {
        return NameCus;
    }

    public void setNameCus(String NameCus) {
        this.NameCus = NameCus;
    }

    public String getPhoneCus() {
        return PhoneCus;
    }

    public void setPhoneCus(String PhoneCus) {
        this.PhoneCus = PhoneCus;
    }

    public Double getPaid() {
        return Paid;
    }

    public void setPaid(Double Paid) {
        this.Paid = Paid;
    }

    public Double getOwe() {
        return Owe;
    }

    public void setOwe(Double Owe) {
        this.Owe = Owe;
    }

    public Double getTotalDebt() {
        return TotalDebt;
    }

    public void setTotalDebt(Double TotalDebt) {
        this.TotalDebt = TotalDebt;
    }

    public String getIdOwe() {
        return IdOwe;
    }

    public void setIdOwe(String IdOwe) {
        this.IdOwe = IdOwe;
    }
    
}
