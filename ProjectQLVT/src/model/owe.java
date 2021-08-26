/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Mun Chan
 */
public class owe {
    String IdOwe;
    String NameCus;
    String PhoneCus;
    String EmailCus;
    String AddressCus;
    String PaymentForm;
    String status;
    private ArrayList<dtm> allPayOwe;
    Double Paid;
    Double Owe;
    Double TotalDebt;
    String OrdID;

    public owe(String IdOwe, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String PaymentForm, ArrayList<dtm> allPayOwe, Double Paid, Double Owe) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.PaymentForm = PaymentForm;
        this.allPayOwe = allPayOwe;
        this.Paid = Paid;
        this.Owe = Owe;
    }

    public owe(String IdOwe, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String PaymentForm, Double Paid, Double Owe) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.PaymentForm = PaymentForm;
        this.Paid = Paid;
        this.Owe = Owe;
    }

    public owe(String IdOwe, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String PaymentForm, Double Paid, Double Owe, Double TotalDebt) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.PaymentForm = PaymentForm;
        this.Paid = Paid;
        this.Owe = Owe;
        this.TotalDebt = TotalDebt;
    }

    public owe(String IdOwe, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String PaymentForm, Double Paid, Double Owe, Double TotalDebt, String OrdID) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.PaymentForm = PaymentForm;
        this.Paid = Paid;
        this.Owe = Owe;
        this.TotalDebt = TotalDebt;
        this.OrdID = OrdID;
    }

    public owe(String IdOwe, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String PaymentForm, Double Paid, Double Owe, Double TotalDebt, String OrdID, String status) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.PaymentForm = PaymentForm;
        this.Paid = Paid;
        this.Owe = Owe;
        this.TotalDebt = TotalDebt;
        this.status = status;
    }

    public owe(String IdOwe, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String PaymentForm, String status, Double Paid, Double Owe, Double TotalDebt) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.PaymentForm = PaymentForm;
        this.status = status;
        this.Paid = Paid;
        this.Owe = Owe;
        this.TotalDebt = TotalDebt;
    }

    public owe(String IdOwe, String NameCus, String PhoneCus, String status, Double Paid, Double Owe, Double TotalDebt) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.status = status;
        this.Paid = Paid;
        this.Owe = Owe;
        this.TotalDebt = TotalDebt;
    }
    
    public String getIdOwe() {
        return IdOwe;
    }

    public owe(String NameCus, String PhoneCus, Double Paid, Double Owe, Double TotalDebt) {
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.Paid = Paid;
        this.Owe = Owe;
        this.TotalDebt = TotalDebt;
    }

    public owe(String IdOwe, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String PaymentForm, String status) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.PaymentForm = PaymentForm;
        this.status = status;
    }
    
    public owe() {
    }
    
    public void setIdOwe(String IdOwe) {
        this.IdOwe = IdOwe;
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

    public String getPaymentForm() {
        return PaymentForm;
    }

    public void setPaymentForm(String PaymentForm) {
        this.PaymentForm = PaymentForm;
    }

    public ArrayList<dtm> getAllPayOwe() {
        return allPayOwe;
    }

    public void setAllPayOwe(ArrayList<dtm> allPayOwe) {
        this.allPayOwe = allPayOwe;
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

    public String getOrdID() {
        return OrdID;
    }

    public void setOrdID(String OrdID) {
        this.OrdID = OrdID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
