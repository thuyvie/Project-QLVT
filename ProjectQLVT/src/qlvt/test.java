package qlvt;


import java.sql.ResultSet;
import util.DBConnect;
import java.sql.PreparedStatement;
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thanh
 */
public class test {
    public static ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null;
	public static PreparedStatement pst1 = null, pst2 = null, pst3 = null, pst4 = null, pst5 = null;
	public static Integer kho, mua, thieu, du, spkho;
	public static String sql1, sql2, sql3, sql4, sql5;
        
    public static void main(String[] args) throws SQLException {
        Connection con = DBConnect.getConnect();
        String sql1 = "SELECT order_detail.OrderID,order_detail.OrderID,order_detail.IDProduct,SUM(order_detail.qty) AS 'quantity',product.itemCode,product.price,(SUM(order_detail.qty)*product.price) AS'total' FROM order_detail,product\n"
                + "WHERE order_detail.IDProduct=product.itemCode AND order_detail.OrderID=(SELECT orders.OrdID \n"
                + "FROM orders ORDER BY orders.OrdID DESC LIMIT 1)\n"
                + "GROUP BY order_detail.IDProduct";
       pst1 = con.prepareStatement(sql1);
       rs1=pst1.executeQuery();
       while(rs1.next()){
           mua=rs1.getInt("quantity");
           String spmua=rs1.getString("IDProduct");
           
           sql2="SELECT warehouse.*,warehouse.ProductID, warehouse.Inventory,warehouse.ID\n" +
"FROM warehouse\n" +
"WHERE warehouse.ProductID = '"+spmua+"' and warehouse.Inventory !=0";
           pst2 = con.prepareStatement(sql2);
       rs2=pst2.executeQuery();     
               System.out.println("mua : " +mua +" spmua: "+spmua);
           while(rs2.next()){
               System.out.println("san pham trong kho : "+ rs2.getString("ProductID")+"so luong kho " +rs2.getInt("Inventory"));
               if(rs2.getInt("Inventory") >= mua){
                   du=rs2.getInt("Inventory") - mua;
                   System.out.println("du: "+du +" cap nhat du vao kho theo kho_id");
                   mua=0;
                   String sql3 = "Update warehouse set Inventory = '"+du+"' where warehouse.ID='"+rs2.getInt("ID")+"' ";
                   pst3 = con.prepareStatement(sql3);
                    pst3.execute();
                   System.out.println(sql3);
                   
               }
               else{
                   while (mua > rs2.getInt("Inventory") && mua > 0){
                       mua = mua - rs2.getInt("Inventory");
                       System.out.println("so luong thieu: "+ mua+"cap nhat inventory = 0 vao warehouse");
                       String sql3 = "Update warehouse set Inventory = 0 where warehouse.ID='"+rs2.getInt("ID")+"' ";
                       pst3 = con.prepareStatement(sql3);
                        pst3.execute();
                   System.out.println(sql3);
                   }
               
           }
               System.out.println("-------------------------------------");
           }
           
           
       }
       
    }
}
