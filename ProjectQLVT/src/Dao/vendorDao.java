/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.vendorlot;
import util.DBConnect;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Mun Chan
 */
public class vendorDao {
    public boolean insert(vendorlot ven)
            throws Exception {
        String sql = "INSERT INTO vendor( vendorID, vendorname, vendorphone, vendoraddress, vendoremail)"
                + "values(?,?,?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, ven.getVendorID());
                pstmt.setString(2, ven.getVendorname());
                pstmt.setString(3, ven.getVendorphone());
                pstmt.setString(4, ven.getVendoraddress());
                pstmt.setString(5, ven.getVendoremail());
            return pstmt.executeUpdate() > 0;
        }

    }
    public boolean update(vendorlot ven)
            throws Exception {
        String sql = "update vendor"
                + " set vendorname=?, vendorphone =?,vendoraddress=?, vendoremail = ? "
                + " where vendorID= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(5, ven.getVendorID());
                pstmt.setString(1, ven.getVendorname());
                pstmt.setString(2, ven.getVendorphone());
                pstmt.setString(3, ven.getVendoraddress());
                pstmt.setString(4, ven.getVendoremail());
            return pstmt.executeUpdate() > 0;
        }
    }
    public vendorlot findByName(String vendorname)
            throws Exception{
        String sql= "select* from vendor where vendorname= '"+vendorname+"'";
        try(
            Connection con= DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
            try(ResultSet rs= pstmt.executeQuery()){
                while(rs.next()){
                    vendorlot ven = new vendorlot();
                    ven.setVendorID(rs.getString("vendorID"));
                    ven.setVendorname(rs.getString("vendorname"));
                    ven.setVendoraddress(rs.getString("vendoraddress"));
                    ven.setVendorphone(rs.getString("vendorphone"));
                    ven.setVendoremail(rs.getString("vendoremail"));
                    return ven;
                }
            }
            
        }
        return null;
    }
    public ObservableList<vendorlot> findAll() {
        ObservableList<vendorlot> listven = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM vendorlot ORDER BY vendorID DESC";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
               vendorlot ven = new vendorlot();
                ven.setVendorID(rs.getString("vendorID"));
                ven.setVendorname(rs.getString("vendorname"));
                ven.setVendorphone("vendorphone");
                ven.setVendoraddress(rs.getString("vendoraddress"));
                ven.setVendoremail(rs.getString("vendoremail"));
                listven.add(ven);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listven ;
    }
}
