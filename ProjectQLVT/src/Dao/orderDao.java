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
import model.customer;
import model.ord;
import util.DBConnect;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.dtmTM;
import model.order2;
import model.orderdetail;
import model.payment;
import model.product;
import util.CrudUtil;
import java.sql.SQLException;
import java.util.Random;
/**
 *
 * @author Mun Chan
 */

public class orderDao {
    
   public boolean insert(order2 ord2) throws Exception{
       String sql = "INSERT INTO orders( OrdID, NameCus, PhoneCus,EmailCus,AddressCus,dateOrd, timeOrd)"
                + "values(?,?,?,?,?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
                pstmt.setString(1, ord2.getOrdID());
                pstmt.setString(2, ord2.getNameCus());
                pstmt.setString(3, ord2.getPhoneCus());
                pstmt.setString(4, ord2.getEmailCus());
                pstmt.setString(5, ord2.getAddressCus());
                pstmt.setString(6, ord2.getDateOrd());
                pstmt.setString(7, ord2.getTimeOrd());
//                pstmt.setInt(7, ord2.getIDEmp());
            return pstmt.executeUpdate() > 0;
        }
   }
   public boolean insertOrdDetail(orderdetail ordDetail) throws Exception{
      String sql = "INSERT INTO order_detail( OrderID, IDProduct, qty, Price, Total)"
                + "values(?,?,?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
                pstmt.setString(1, ordDetail.getOrderID());
                pstmt.setString(2, ordDetail.getIDProduct());
                pstmt.setString(3, ordDetail.getQty());
                pstmt.setString(4, ordDetail.getPrice());
                pstmt.setDouble(5, ordDetail.getTotal());
            return pstmt.executeUpdate() > 0;
        }
   }
   private boolean addOrdDtail(ord dto) throws SQLException, ClassNotFoundException, Exception{
        for (dtmTM ord : dto.getAllOrderDetail()) {
            orderdetail orderTable = new orderdetail(dto.getOrdID(), ord.getCode(), ord.getQTY(), ord.getPrice(), ord.getTotal());
            System.out.println("");
            orddetailDao orddt = new orddetailDao();
            boolean isAddedOrderDetails = orddt.insertOrdDetail(orderTable);
            if (!isAddedOrderDetails) {
                return false;
            }
        }
        return true;
   }
   private boolean updateOrdStock(ArrayList<dtmTM> orderItems) throws Exception{
       for(dtmTM orderdetail : orderItems){
           boolean isUpdateStock = updateOrdStock(orderdetail);
           if(!isUpdateStock){
               return false;
           }
       }
       return true;
   }
   public boolean updateOrdStock (dtmTM orderdetail) throws Exception{
       String sql = "update product set qty=qty-? where itemCode=?";       
               return CrudUtil.executeUpdate(sql,orderdetail.getQTY(), orderdetail.getCode());
   }
   public static String getRandomNumberString() {
        {
            Random rand = new Random();
            int rand_int1 = rand.nextInt(100000);
            return String.valueOf(rand_int1);
        }
    }

   public boolean placeOrder(ord o) throws Exception{
       Connection con = DBConnect.getConnect();
       con.setAutoCommit(false);
       order2 ord2 = new order2(o.getOrdID(),o.getNameCus(),o.getPhoneCus(),o.getEmailCus(),o.getAddressCus(),o.getDateOrd(),o.getTimeOrd());
       boolean add = insert(ord2);
       try{
           if(add){
               boolean ordDetailAdd = addOrdDtail(o);
               if(ordDetailAdd){
                   paymentDao payDao = new paymentDao();
                   boolean ispayAdd = payDao.insert(
                   new payment(getRandomNumberString(),o.getNameCus(), o.getAmount(),o.getOrdID(), o.getNote())
                   );
                   if(ispayAdd){
                       boolean updateStock = updateOrdStock(o.getAllOrderDetail());
                       if(updateStock){
                           con.commit();
                           return true;
                       }
                   }
               }
               con.commit();
               return true;
           } else{
               con.rollback();
               return false;
           }
       }finally{
           con.setAutoCommit(true);
       }
   }
    public boolean update(order2 ord2) throws Exception{
      String sql = "update orders"
                + " set namecus=?, phonecus =?, emailcus = ?,dateOrd=?, timeOrd = ?, IDEmp=?"
                + " where OrdID= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(8, ord2.getOrdID());
                pstmt.setString(1, ord2.getNameCus());
                pstmt.setString(2, ord2.getPhoneCus());
                pstmt.setString(3, ord2.getEmailCus());
                pstmt.setString(4, ord2.getAddressCus());
                pstmt.setString(5, ord2.getDateOrd());
                pstmt.setString(6, ord2.getTimeOrd());
                pstmt.setInt(7, ord2.getIDEmp());
            return pstmt.executeUpdate() > 0;
        }
   }
    
     public customer searchCus(String PhoneCus) {
        ObservableList<customer> listcustomer = FXCollections.observableArrayList();
         String sql = "SELECT NameCus, PhoneCus,AddressCus,EmailCus FROM  customer  where PhoneCus='" + PhoneCus + "'";
        Statement stmt;
        try (
            Connection con = DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
             try(ResultSet rs= pstmt.executeQuery()){         
                while (rs.next()) {
                customer cus = new customer();
                cus.setNameCus(rs.getString("NameCus"));
                cus.setPhoneCus(rs.getString("PhoneCus"));
                cus.setAddressCus(rs.getString("AddressCus"));
                cus.setEmailCus(rs.getString("EmailCus"));
                return cus;
                }
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;

    }
     
      public product searchPro(String itemCode) {
        ObservableList<customer> listproduct = FXCollections.observableArrayList();
         String sql = "SELECT itemCode,namepro, price FROM  product  where itemCode='" + itemCode + "'";
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
                pro.setPrice(rs.getDouble("price"));
                return pro;
                }
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;

    }
       public product searchNamePro(String namepro) {
        ObservableList<customer> listproduct = FXCollections.observableArrayList();
         String sql = "SELECT itemCode,namepro, price FROM  product  where namepro='" + namepro + "'";
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
                pro.setPrice(rs.getDouble("price"));
                return pro;
                }
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;

    }
}
