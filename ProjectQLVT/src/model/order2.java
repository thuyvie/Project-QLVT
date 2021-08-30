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
public class order2 {
    String OrdID;
    String NameCus;
    String PhoneCus;
    String EmailCus;
    String AddressCus;
    String dateOrd;
    String timeOrd;
    int IDEmp;
    String NameEmp;
    private emp e;

    public order2(String OrdID, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, int IDEmp, String NameEmp, emp e) {
        this.OrdID = OrdID;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.IDEmp = IDEmp;
        this.NameEmp = NameEmp;
        this.e = e;
    }

    public order2(String OrdID, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, int IDEmp) {
        this.OrdID = OrdID;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.IDEmp = IDEmp;
    }

    public order2(String OrdID, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd) {
        this.OrdID = OrdID;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
    }

    public order2(String NameCus,String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd) {
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
    }
    
    public order2() {
    }

    public String getOrdID() {
        return OrdID;
    }

    public void setOrdID(String OrdID) {
        this.OrdID = OrdID;
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

    public String getEmailCus() {
        return EmailCus;
    }

    public void setEmailCus(String EmailCus) {
        this.EmailCus = EmailCus;
    }

    public String getAddressCus() {
        return AddressCus;
    }

    public void setAddressCus(String AddressCus) {
        this.AddressCus = AddressCus;
    }

    public String getDateOrd() {
        return dateOrd;
    }

    public void setDateOrd(String dateOrd) {
        this.dateOrd = dateOrd;
    }

    public String getTimeOrd() {
        return timeOrd;
    }

    public void setTimeOrd(String timeOrd) {
        this.timeOrd = timeOrd;
    }

    public int getIDEmp() {
        return IDEmp;
    }

    public void setIDEmp(int IDEmp) {
        this.IDEmp = IDEmp;
    }

    public String getNameEmp() {
        return NameEmp;
    }

    public void setNameEmp(String NameEmp) {
        this.NameEmp = NameEmp;
    }

    public emp getE() {
        return e;
    }

    public void setE(emp e) {
        this.e = e;
    }
    
}
