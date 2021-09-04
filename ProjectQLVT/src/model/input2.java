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
public class input2 {
    String InputID;
    String Date;
    Double Total;

    public input2() {
    }

    public input2(String InputID, String Date, Double Total) {
        this.InputID = InputID;
        this.Date = Date;
        this.Total = Total;
    }

    public String getInputID() {
        return InputID;
    }

    public void setInputID(String InputID) {
        this.InputID = InputID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }
    
}
