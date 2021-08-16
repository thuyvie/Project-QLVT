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
public class catepro {
    int ID;
    String NameCate;

    public catepro() {
    }

    public catepro(int ID, String NameCate) {
        this.ID = ID;
        this.NameCate = NameCate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameCate() {
        return NameCate;
    }

    public void setNameCate(String NameCate) {
        this.NameCate = NameCate;
    }
    
}
