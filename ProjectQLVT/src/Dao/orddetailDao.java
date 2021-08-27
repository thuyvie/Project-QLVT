/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.orderdetail;
import util.DBConnect;

/**
 *
 * @author Mun Chan
 */
public class orddetailDao {
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
     public boolean updateOrdDetail(orderdetail ordDetail) throws Exception{
       String sql = "update order_detail set IDProduct=?, qty=?, Price=? where OrderID=?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
               pstmt.setString(1, ordDetail.getOrderID());
                pstmt.setString(2, ordDetail.getIDProduct());
                pstmt.setString(4, ordDetail.getQty());
                pstmt.setString(4, ordDetail.getPrice());
            return pstmt.executeUpdate() > 0;
        }
   }
}
