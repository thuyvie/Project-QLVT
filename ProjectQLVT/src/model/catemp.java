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
    String IDCate;
    String EmpCate;

    public catemp(String ID, String EmpCate) {
        this.IDCate = ID;
        this.EmpCate = EmpCate;
    }

    public catemp() {
    }

    public String getID() {
        return IDCate;
    }

    public void setID(String IDCate) {
        this.IDCate = IDCate;
    }

    public String getEmpCate() {
        return EmpCate;
    }

    public void setEmpCate(String EmpCate) {
        this.EmpCate = EmpCate;
    }
    
}
