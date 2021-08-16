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
    int ID;
    String NameEmp;
    String PhoneEmp;
    String Account;
    String Password;
    Double Salary;
    int IDCateEmp;
    String EmpCate;
    private catemp cate;

    public emp() {
    }

    public emp(int ID, String NameEmp, String PhoneEmp, String Account, String Password, Double Salary, int IDCateEmp, String EmpCate, catemp cate) {
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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

    public int getIDCateEmp() {
        return IDCateEmp;
    }

    public void setIDCateEmp(int IDCateEmp) {
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
