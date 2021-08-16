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
public class catemp {
    int IDCate;
    String EmpCate;

    public catemp(int IDCate, String EmpCate) {
        this.IDCate = IDCate;
        this.EmpCate = EmpCate;
    }

    public catemp() {
    }

    public int getIDCate() {
        return IDCate;
    }

    public void setIDCate(int IDCate) {
        this.IDCate = IDCate;
    }

    public String getEmpCate() {
        return EmpCate;
    }

    public void setEmpCate(String EmpCate) {
        this.EmpCate = EmpCate;
    }
    
}
