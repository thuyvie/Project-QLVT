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
public class vendorlot {
    String vendorID;
    String vendorname;
    String vendorphone;
    String vendoraddress;
    String vendoremail;

    public vendorlot() {
    }

    public vendorlot(String vendorID, String vendorname, String vendorphone, String vendoraddress, String vendoremail) {
        this.vendorID = vendorID;
        this.vendorname = vendorname;
        this.vendorphone = vendorphone;
        this.vendoraddress = vendoraddress;
        this.vendoremail = vendoremail;
    }

    public vendorlot(String vendorID, String vendorname) {
        this.vendorID = vendorID;
        this.vendorname = vendorname;
    }
    
    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendorphone() {
        return vendorphone;
    }

    public void setVendorphone(String vendorphone) {
        this.vendorphone = vendorphone;
    }

    public String getVendoraddress() {
        return vendoraddress;
    }

    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }

    public String getVendoremail() {
        return vendoremail;
    }

    public void setVendoremail(String vendoremail) {
        this.vendoremail = vendoremail;
    }
    
}
