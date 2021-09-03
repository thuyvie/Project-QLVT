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
import model.product;
import model.warehouse;
import util.DBConnect;

/**
 *
 * @author Mun Chan
 */
public class wareDao {
     public boolean insert(warehouse ware)throws Exception {
        String sql = "INSERT INTO warehouse( ID, ProductID, Inventory, Amountinput, Dateinput, OriginalPrice)"
                + "values(?,?,?,?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, ware.getID());
                pstmt.setString(2, ware.getProductID());
                pstmt.setString(3, ware.getInventory());
                pstmt.setString(4, ware.getAmountInput());
                pstmt.setString(5, ware.getDateInput());
                pstmt.setDouble(6, ware.getOriginalPrice());
            return pstmt.executeUpdate() > 0;
        }

    }
     public boolean update(warehouse ware)
            throws Exception {
        String sql = "update warehouse"
                + " set  ProductID=?, Inventory = ?,Amountinput=?,Dateinput = ?, OriginalPrice=? "
                + " where ID= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(6, ware.getID());
//                pstmt.setString(2, ware.getPro().getItemCode());
                pstmt.setString(1, ware.getProductID());
                pstmt.setString(2, ware.getInventory());
                pstmt.setString(3, ware.getAmountInput());
                pstmt.setString(4, ware.getDateInput());
                pstmt.setDouble(5, ware.getOriginalPrice());             
            return pstmt.executeUpdate() > 0;
        }
    }
     public warehouse searchPro(String ProductID) {
        ObservableList<warehouse> listproduct = FXCollections.observableArrayList();
         String sql = "SELECT  warehouse.ID, warehouse.ProductID,product.itemCode,warehouse.Inventory,warehouse.Amountinput,warehouse.Dateinput,warehouse.OriginalPrice FROM warehouse INNER JOIN product ON  warehouse.ProductID= product.itemCode where ProductID='" + ProductID + "'";
        Statement stmt;
        try (
            Connection con = DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
             try(ResultSet rs= pstmt.executeQuery()){         
                while (rs.next()) {
                warehouse pro = new warehouse();
                pro.setID(rs.getString("ID"));
                pro.setProductID(rs.getString("ProductID"));
                pro.setInventory(rs.getString("Inventory"));
                pro.setAmountInput(rs.getString("AmountInput"));
                pro.setDateInput(rs.getString("DateInput"));
                pro.setOriginalPrice(rs.getDouble("OriginalPrice"));
                return pro;
                }
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;

    }
}
