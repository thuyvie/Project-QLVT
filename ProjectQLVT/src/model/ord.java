/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
/**
 *
 * @author Mun Chan
 */
public class ord {
    String OrdID;
    private customer idcus;
    String NameCus;
    String PhoneCus;
    String EmailCus;
    String AddressCus;
    String dateOrd;
    String timeOrd;
    String Payment;
    int IDEmp;
    String NameEmp;
    private emp e;
    private ArrayList<dtmTM> allOrderDetail;
    private Double amount;
    String note;
    int qty;
    Double Price;
    Double Total;
    String itemCode;
    String namepro;
    public ord() {
    }

    public ord(String OrdID, customer idcus, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, String Payment, int IDEmp, String NameEmp, emp e) {
        this.OrdID = OrdID;
        this.idcus = idcus;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.Payment = Payment;
        this.IDEmp = IDEmp;
        this.NameEmp = NameEmp;
        this.e = e;
        
    }

    public ord(String OrdID, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, int IDEmp, Double amount) {
        this.OrdID = OrdID;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.IDEmp = IDEmp;
        this.amount = amount;
    }

    public ord(String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, int IDEmp, Double amount) {
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.IDEmp = IDEmp;
        this.amount = amount;
    }

    public ord(String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, Double amount) {
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.amount = amount;
    }

    public ord(String OrdID, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, String Payment, ArrayList<dtmTM> allOrderDetail, Double amount) {
        this.OrdID = OrdID;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.Payment = Payment;
        this.allOrderDetail = allOrderDetail;
        this.amount = amount;
    }

    public ord( String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, ArrayList<dtmTM> allOrderDetail, Double amount) {
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.allOrderDetail = allOrderDetail;
        this.amount = amount;
    }

    public ord(String OrdID, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, ArrayList<dtmTM> allOrderDetail, Double amount) {
        this.OrdID = OrdID;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.allOrderDetail = allOrderDetail;
        this.amount = amount;
    }

    public ord(String OrdID, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, ArrayList<dtmTM> allOrderDetail, Double amount, String note) {
        this.OrdID = OrdID;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.allOrderDetail = allOrderDetail;
        this.amount = amount;
        this.note = note;
    }

    public ord(String OrdID, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String dateOrd, String timeOrd, String itemCode, String namepro, int qty, Double Price, Double Total) {
        this.OrdID = OrdID;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.dateOrd = dateOrd;
        this.timeOrd = timeOrd;
        this.itemCode = itemCode;
        this.namepro = namepro;
        this.qty = qty;
        this.Price = Price;
        this.Total = Total;
    }
    
    public String getOrdID() {
        return OrdID;
    }

    public void setOrdID(String OrdID) {
        this.OrdID = OrdID;
    }

    

    
    

   

    public customer getIdcus() {
        return idcus;
    }

    public void setIdcus(customer idcus) {
        this.idcus = idcus;
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

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String Payment) {
        this.Payment = Payment;
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

    public ArrayList<dtmTM> getAllOrderDetail() {
        return allOrderDetail;
    }

    public void setAllOrderDetail(ArrayList<dtmTM> allOrderDetail) {
        this.allOrderDetail = allOrderDetail;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getNamepro() {
        return namepro;
    }

    public void setNamepro(String namepro) {
        this.namepro = namepro;
    }
    
}
