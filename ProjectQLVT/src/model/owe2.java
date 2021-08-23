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
public class owe2 {
     String IdOwe;
    String NameCus;
    String PhoneCus;
    String EmailCus;
    String AddressCus;
    String PaymentForm;
    String status;
    public owe2(String IdOwe, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String PaymentForm) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.PaymentForm = PaymentForm;
    }

    public owe2(String IdOwe, String NameCus, String PhoneCus, String EmailCus, String AddressCus, String PaymentForm, String status) {
        this.IdOwe = IdOwe;
        this.NameCus = NameCus;
        this.PhoneCus = PhoneCus;
        this.EmailCus = EmailCus;
        this.AddressCus = AddressCus;
        this.PaymentForm = PaymentForm;
        this.status = status;
    }
    
    public String getIdOwe() {
        return IdOwe;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
