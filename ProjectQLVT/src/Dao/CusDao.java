/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBConnect;
import java.sql.PreparedStatement;
import model.customer;
/**
 *
 * @author thanh
 */
public class CusDao {
    public boolean insert(customer cus)throws Exception {
        String sql = "INSERT INTO customer( NameCus, PhoneCus, EmailCus, AddressCus)"
                + "values(?,?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, cus.getNameCus());
                pstmt.setString(2, cus.getPhoneCus());
                pstmt.setString(3, cus.getEmailCus());
                pstmt.setString(4, cus.getAddressCus());
                
                
            return pstmt.executeUpdate() > 0;
        }

    }
     public boolean update(customer cus)
            throws Exception {
        String sql = "update customer"
                + " set NameCus=?, PhoneCus =?, EmailCus = ?,AddressCus=?"
                + " where ID= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(5, cus.getID());
                pstmt.setString(1, cus.getNameCus());
                pstmt.setString(2, cus.getPhoneCus());
                pstmt.setString(3, cus.getEmailCus());
                pstmt.setString(4, cus.getAddressCus());                
            return pstmt.executeUpdate() > 0;
        }
    }
     public customer searchCus(String PhoneCus) {
        ObservableList<customer> listpcustomer = FXCollections.observableArrayList();
         String sql = "SELECT customer.NameCus,customer.PhoneCus,customer.EmailCus,customer.Address FROM customer where PhoneCus= '"+PhoneCus +"' ";
        Statement stmt;
        try (
            Connection con = DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
             try(ResultSet rs= pstmt.executeQuery()){         
                while (rs.next()) {
                customer cus = new customer();
                cus.setID(rs.getString("ID"));
                cus.setNameCus(rs.getString("NameCus"));
                cus.setPhoneCus(rs.getString("PhoneCus"));
                cus.setEmailCus(rs.getString("EmailCus"));
                cus.setAddressCus(rs.getString("AddressCus"));
                return cus;
                }
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;

    }
}
