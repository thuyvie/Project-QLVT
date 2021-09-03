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
import model.product;
import util.DBConnect;
import java.sql.PreparedStatement;
import model.catepro;
import model.vendorlot;

/**
 *
 * @author Mun Chan
 */
public class productDao {
    public boolean insert(product pro)throws Exception {
        String sql = "INSERT INTO product( itemCode, namepro, vendorid, description, size, price, batchid, IDCate)"
                + "values(?,?,?,?,?,?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, pro.getItemCode());
                pstmt.setString(2, pro.getNamepro());
                pstmt.setString(3, pro.getVen().getVendorID());
                pstmt.setString(4, pro.getDescription());
                pstmt.setString(5, pro.getSize());
                pstmt.setDouble(6, pro.getPrice());
                pstmt.setString(7, pro.getBatchid());
                pstmt.setString(8, pro.getCate().getID());
            return pstmt.executeUpdate() > 0;
        }

    }
     public boolean update(product pro)
            throws Exception {
        String sql = "update product"
                + " set namepro=?, vendorid =?, description = ?,size=?, price = ?, batchid=?, IDCate=? "
                + " where itemCode= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(8, pro.getItemCode());
                pstmt.setString(1, pro.getNamepro());
                pstmt.setString(2, pro.getVen().getVendorID());
                pstmt.setString(3, pro.getDescription());
                pstmt.setString(4, pro.getSize());
                pstmt.setDouble(5, pro.getPrice());
                pstmt.setString(6, pro.getBatchid());
                pstmt.setString(7, pro.getCate().getID());                
            return pstmt.executeUpdate() > 0;
        }
    }
     public product searchPro(String namepro) {
        ObservableList<product> listproduct = FXCollections.observableArrayList();
         String sql = "SELECT product.itemCode, product.namepro, product.vendorid, vendor.vendorname, product.description, product.size, product.price, product.batchid, product.IDCate, cateproduct.NameCate FROM product INNER JOIN vendor ON product.vendorid = vendor.vendorID INNER JOIN cateproduct ON product.IDCate = cateproduct.ID where namepro='" + namepro + "'";
        Statement stmt;
        try (
            Connection con = DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
             try(ResultSet rs= pstmt.executeQuery()){         
                while (rs.next()) {
                product pro = new product();
                pro.setItemCode(rs.getString("itemCode"));
                pro.setNamepro(rs.getString("namepro"));
                pro.setVen(new vendorlot(rs.getString("vendorID"), rs.getString("vendorname")));
                pro.setDescription(rs.getString("description"));
                pro.setSize(rs.getString("size"));
                pro.setPrice(rs.getDouble("price"));
                pro.setBatchid(rs.getString("batchid"));
                pro.setCate(new catepro(rs.getString("ID") , rs.getString("NameCate")));
                return pro;
                }
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;

    }
     public product searchProduct(String itemCode) {
        ObservableList<product> listproduct = FXCollections.observableArrayList();
         String sql = "SELECT product.itemCode, product.namepro, product.vendorid, vendor.vendorname, product.description, product.size, product.price, product.batchid, product.IDCate, cateproduct.NameCate FROM product INNER JOIN vendor ON product.vendorid = vendor.vendorID INNER JOIN cateproduct ON product.IDCate = cateproduct.ID where namepro='" + itemCode + "'";
        Statement stmt;
        try (
            Connection con = DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
             try(ResultSet rs= pstmt.executeQuery()){         
                while (rs.next()) {
                product pro = new product();
                pro.setItemCode(rs.getString("itemCode"));
                pro.setNamepro(rs.getString("namepro"));
                pro.setVen(new vendorlot(rs.getString("vendorID"), rs.getString("vendorname")));
                pro.setDescription(rs.getString("description"));
                pro.setSize(rs.getString("size"));
                pro.setPrice(rs.getDouble("price"));
                pro.setBatchid(rs.getString("batchid"));
                pro.setCate(new catepro(rs.getString("ID") , rs.getString("NameCate")));
                return pro;
                }
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;

    }
}
