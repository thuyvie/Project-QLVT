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
import model.output;
import model.outputdetail;
import model.wh;
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
    public boolean insertOutputDetail(outputdetail out) throws Exception{
      String sql = "INSERT INTO output_detail(ID, OutputID, WareHouseID, AmountOutput,Price, IDProduct)"
//              + "SELECT warehouse.ID FROM output_detail inner join warehouse on warehouse.ID = output_detail.WareHouseID WHERE ProductID=?"
//              +"SELECT warehouse.OriginalPrice,order_detail.qty,order_detail.IDProduct, output.ID,output_detail.OutputID FROM warehouse,order_detail,output, output_detail WHERE order_detail.OrderID = output.OrderID and output.ID = output_detail.OutputID and output_detail.WareHouseID = warehouse.ID";
               + "values (?,?,?,?,?,?)";
//      SELECT warehouse.ID,warehouse.ProductID FROM warehouse =
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
                pstmt.setString(1, out.getID());
                pstmt.setString(2, out.getOutputID());
                pstmt.setString(3, "SELECT warehouse.ID FROM output_detail inner join warehouse on warehouse.ID = output_detail.WareHouseID WHERE ProductID=?");
                pstmt.setString(4,out.getAmountoutput());
                pstmt.setString(5, out.getPrice());
                pstmt.setString(6, out.getProductID());
            return pstmt.executeUpdate() > 0;
        }
   }
    public boolean insertOutput(output out) throws Exception{
      String sql = "INSERT INTO output(ID, DateInvoice, OrderID)"
                + "values(?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
                pstmt.setString(1, out.getID());
                pstmt.setString(2, out.getDate());
                pstmt.setString(3, out.getOrderID());
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
    private boolean addOutDetail(ord dto) throws SQLException, ClassNotFoundException, Exception{
        for (dtmTM ord : dto.getAllOrderDetail()) {
            outputdetail orderTable = new outputdetail(getRandomNumberString(),dto.getOrdID(), ord.getWareHouseID(),ord.getQTY(), ord.getPrice(), ord.getCode());
            System.out.println("");
            boolean isAddedOrderDetails = insertOutputDetail(orderTable);
            if (!isAddedOrderDetails) {
                return false;
            }
        }
        return true;
   }
    private boolean addOrdDetail(ord dto) throws SQLException, ClassNotFoundException, Exception{
        for (dtmTM ord : dto.getAllOrderDetail()) {
            orderdetail orderTable = new orderdetail(dto.getOrdID(), ord.getCode(), ord.getQTY(), ord.getPrice(), ord.getTotal());
            System.out.println("");
            orddetailDao orddt = new orddetailDao();
            boolean isAddedOrderDetails = orddt.updateOrdDetail(orderTable);
            if (!isAddedOrderDetails) {
                return false;
            }
        }
        return true;
   }
   private boolean updateOrdStock(ArrayList<dtmTM> orderItems) throws Exception{
       for(dtmTM orderdetail : orderItems){
           boolean isUpdateStock = updateOrdStock2(orderdetail);
           if(!isUpdateStock){
               return false;
           }
       }
       return true;
   }
    
     
   public boolean updateOrdStock (dtmTM orderdetail) throws Exception{
       String sql = "Update warehouse inner join product on warehouse.ProductID = product.itemCode set inventory= inventory-? where itemCode=?";       
               return CrudUtil.executeUpdate(sql,orderdetail.getQTY(), orderdetail.getCode());
   }
    public boolean updateOrdStock2 (dtmTM orderdetail) throws Exception{
       String sql = "Update warehouse set Inventory = Inventory-? where ProductID=? AND warehouse.ID = (select Min(ID) from warehouse)";       
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
                           boolean ouput = insertOutput(
                           new output (o.getOrdID(),o.getDateOrd(),o.getOrdID())
                           );
//                           if(ouput){
//                           boolean outdetail = addOutDetail(o);
//                           }
//                           if(outdetail){
//                           con.commit();
//                           return true;
//                           }
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
    public boolean placeOrder3(ord o) throws Exception{
       Connection con = DBConnect.getConnect();
       con.setAutoCommit(false);
       order2 ord2 = new order2(o.getOrdID(),o.getNameCus(),o.getPhoneCus(),o.getEmailCus(),o.getAddressCus(),o.getDateOrd(),o.getTimeOrd());
       boolean add = update(ord2);
       try{
           if(add){
               boolean ordDetailAdd = addOrdDetail(o);
               if(ordDetailAdd){
                   paymentDao payDao = new paymentDao();
                   boolean ispayAdd = payDao.update(
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
    public boolean update(payment pay)
            throws Exception {
        String sql = "update payment"
                + " set NameCus=?,  amount =?, OrdID =? ,note = ?"
                + " where payid= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(5, pay.getPayid());
                pstmt.setString(1, pay.getNameCus());
                pstmt.setDouble(2, pay.getAmount());
                pstmt.setString(3, pay.getOrdID());
                pstmt.setString(4, pay.getNote());
            return pstmt.executeUpdate() > 0;
        }
    }
    public boolean update(order2 ord2) throws Exception{
      String sql = "update orders"
                + " set NameCus=?, PhoneCus =?, Emailcus = ?,AddressCus = ? , dateOrd=?, timeOrd = ?"
                + " where OrdID= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(7, ord2.getOrdID());
                pstmt.setString(1, ord2.getNameCus());
                pstmt.setString(2, ord2.getPhoneCus());
                pstmt.setString(3, ord2.getEmailCus());
                pstmt.setString(4, ord2.getAddressCus());
                pstmt.setString(5, ord2.getDateOrd());
                pstmt.setString(6, ord2.getTimeOrd());
            return pstmt.executeUpdate() > 0;
        }
   }
    public boolean update(orderdetail ord2) throws Exception{
      String sql = "update order_detail"
                + " set IDProduct=?, qty =?, Price = ?, Total = ?"
                + " where OrderID= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(5, ord2.getOrderID());
                pstmt.setString(1, ord2.getIDProduct());
                pstmt.setString(2, ord2.getQty());
                pstmt.setString(3, ord2.getPrice());
                pstmt.setDouble(4, ord2.getTotal());
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
        ObservableList<product> listproduct = FXCollections.observableArrayList();
         String sql = "select itemCode,namepro,price from product where itemCode='"+itemCode+"'";
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
//                pro.setW(new wh(rs.getString("ProductID"), rs.getInt("Inventory")));
//                pro.setInventory(rs.getInt("Inventory"));
                return pro;
                }
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;

    }
      //select namepro,itemCode, price, sum(Inventory) AS 'Inventory' from warehouse, product GROUP BY product.itemCode = warehouse.ProductID ='"+itemCode+"'
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
