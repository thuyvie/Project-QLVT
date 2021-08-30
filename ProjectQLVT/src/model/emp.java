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
public class emp {
    String ID;
    String NameEmp;
    String PhoneEmp;
    String Account;
    String Password;
    Double Salary;
    String IDCateEmp;
    String EmpCate;
    private catemp cate;

    public emp() {
    }

    public emp(String ID, String NameEmp, String PhoneEmp, String Account, String Password, Double Salary, String IDCateEmp, String EmpCate, catemp cate) {
        this.ID = ID;
        this.NameEmp = NameEmp;
        this.PhoneEmp = PhoneEmp;
        this.Account = Account;
        this.Password = Password;
        this.Salary = Salary;
        this.IDCateEmp = IDCateEmp;
        this.EmpCate = EmpCate;
        this.cate = cate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNameEmp() {
        return NameEmp;
    }

    public void setNameEmp(String NameEmp) {
        this.NameEmp = NameEmp;
    }

    public String getPhoneEmp() {
        return PhoneEmp;
    }

    public void setPhoneEmp(String PhoneEmp) {
        this.PhoneEmp = PhoneEmp;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String Account) {
        this.Account = Account;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double Salary) {
        this.Salary = Salary;
    }

    public String getIDCateEmp() {
        return IDCateEmp;
    }

    public void setIDCateEmp(String IDCateEmp) {
        this.IDCateEmp = IDCateEmp;
    }

    public String getEmpCate() {
        return EmpCate;
    }

    public void setEmpCate(String EmpCate) {
        this.EmpCate = EmpCate;
    }

    public catemp getCate() {
        return cate;
    }

    public void setCate(catemp cate) {
        this.cate = cate;
    }
    
}
