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
public class customer {
    int IDCus;
    String NameCus;
    String PhoneCus;
    String AddressCus;
    String EmailCus;

    public customer() {
    }

    public customer(int IDCus, String NameCus, String PhoneCus, String AddressCus, String EmailCus) {
        this.IDCus = IDCus;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.AddressCus = AddressCus;
        this.EmailCus = EmailCus;
    }

    public int getIDCus() {
        return IDCus;
    }

    public void setIDCus(int IDCus) {
        this.IDCus = IDCus;
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

    public String getAddressCus() {
        return AddressCus;
    }

    public void setAddressCus(String AddressCus) {
        this.AddressCus = AddressCus;
    }

    public String getEmailCus() {
        return EmailCus;
    }

    public void setEmailCus(String EmailCus) {
        this.EmailCus = EmailCus;
    }
    
}