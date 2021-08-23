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
public class owedetail {
    String ID;
    String NameCus;
    String PhoneCus;
    String Paid;
    String Owe;
    String TotalDebt;
    String IdOwe;

    public owedetail(String ID, String Paid, String Owe, String TotalDebt, String IdOwe) {
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

    public String getPaid() {
        return Paid;
    }

    public void setPaid(String Paid) {
        this.Paid = Paid;
    }

    public String getOwe() {
        return Owe;
    }

    public void setOwe(String Owe) {
        this.Owe = Owe;
    }

    public String getTotalDebt() {
        return TotalDebt;
    }

    public void setTotalDebt(String TotalDebt) {
        this.TotalDebt = TotalDebt;
    }

    public String getIdOwe() {
        return IdOwe;
    }

    public void setIdOwe(String IdOwe) {
        this.IdOwe = IdOwe;
    }
    
}
