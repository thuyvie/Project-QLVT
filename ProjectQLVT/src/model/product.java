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
public class product {
    String itemCode;
    String namepro;
    String vendorid; // nha cung cap
    String vendorname;
    String description;
    String size;
    Double price;
    int qty;
    String batchid; // lo hang cung cap
    private vendorlot ven;
    int IDCate;
    String NameCate;
    private catepro cate;
    public product() {
    }

    public product(String itemCode, String namepro, String vendorname, String description, String size, String batchid, String NameCate) {
        this.itemCode = itemCode;
        this.namepro = namepro;
        this.vendorname = vendorname;
        this.description = description;
        this.size = size;
        this.batchid = batchid;
        this.NameCate = NameCate;
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

    public String getVendorid() {
        return vendorid;
    }

    public void setVendorid(String vendorid) {
        this.vendorid = vendorid;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public vendorlot getVen() {
        return ven;
    }

    public void setVen(vendorlot ven) {
        this.ven = ven;
    }

    public catepro getCate() {
        return cate;
    }

    public void setCate(catepro cate) {
        this.cate = cate;
    }

    public int getIDCate() {
        return IDCate;
    }

    public void setIDCate(int IDCate) {
        this.IDCate = IDCate;
    }

    public String getNameCate() {
        return NameCate;
    }

    public void setNameCate(String NameCate) {
        this.NameCate = NameCate;
    }
    
    
    
    public product(String itemCode, String namepro, String vendorid, String vendorname, String description, String size, Double price, int qty, String batchid) {
        this.itemCode = itemCode;
        this.namepro = namepro;
        this.vendorid = vendorid;
        this.vendorname = vendorname;
        this.description = description;
        this.size = size;
        this.price = price;
        this.qty = qty;
        this.batchid = batchid;
        
    }

    public product(vendorlot ven) {
        this.ven = ven;
    }

    public product(String itemCode, String namepro, String vendorid, String vendorname, String description, String size, Double price, int qty, String batchid, vendorlot ven, int IDCate, String NameCate, catepro cate) {
        this.itemCode = itemCode;
        this.namepro = namepro;
        this.vendorid = vendorid;
        this.vendorname = vendorname;
        this.description = description;
        this.size = size;
        this.price = price;
        this.qty = qty;
        this.batchid = batchid;
        this.ven = ven;
        this.IDCate = IDCate;
        this.NameCate = NameCate;
        this.cate = cate;
    }
    
    
   
}
