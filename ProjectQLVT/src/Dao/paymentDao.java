/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.payment;
import util.DBConnect;

/**
 *
 * @author Mun Chan
 */
public class paymentDao {
     public boolean insert(payment pay)
            throws Exception {
        String sql = "INSERT INTO payment( payid, namecus, amount, OrdID, note)"
                + "values(?,?,?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, pay.getPayid());
                pstmt.setString(2, pay.getNameCus());
                pstmt.setDouble(3, pay.getAmount());
                pstmt.setString(4, pay.getOrdID());
                pstmt.setString(5, pay.getNote());
            return pstmt.executeUpdate() > 0;
        }

    }
    public boolean update(payment pay)
            throws Exception {
        String sql = "update payment"
                + " set NameCus=?,  amount =?,OrdID=?,note = ?"
                + " where payid= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, pay.getPayid());
                pstmt.setString(2, pay.getNameCus());
                pstmt.setDouble(3, pay.getAmount());
                pstmt.setString(4, pay.getOrdID());
                pstmt.setString(5, pay.getNote());
            return pstmt.executeUpdate() > 0;
        }
    }
    public payment findByIDOrder(int ordid)
            throws Exception{
        String sql= "select* from payment where ordid= '"+ordid+"'";
        try(
            Connection con= DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
            try(ResultSet rs= pstmt.executeQuery()){
                while(rs.next()){
                    payment p = new payment();
                    p.setPayid(rs.getString("payid"));
                    p.setNameCus(rs.getString("namecus"));
                    p.setAmount(rs.getDouble("amount"));
                    p.setOrdID(rs.getString("OrdID"));
                    p.setNote(rs.getString("note"));
                    return p;
                }
            }
            
        }
        return null;
    }
    public ObservableList<payment> findAll() {
        ObservableList<payment> listpay = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM payment ORDER BY payid DESC";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                payment p = new payment();
                    p.setPayid(rs.getString("payid"));
                    p.setNameCus(rs.getString("namecus"));
                    p.setAmount(rs.getDouble("amount"));
                    p.setOrdID(rs.getString("OrdID"));
                     p.setNote(rs.getString("note"));
                listpay.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listpay ;
    }
}
