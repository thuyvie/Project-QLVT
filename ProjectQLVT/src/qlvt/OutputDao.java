/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlvt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.output_detail;
import util.DBConnect;
import model.output;
/**
 *
 * @author thanh
 */
 
public class OutputDao {
    public boolean insertOutDetail(output_detail opd)throws Exception {
        String sql = "INSERT INTO output_detail(ID,OutputID,WareHouseID,AmountOutput,IDPro,Price)"
                + "values(?,?,?,?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, opd.getID());
                pstmt.setString(2, opd.getOutputID());
                pstmt.setString(3, opd.getWareHouseID());
                pstmt.setInt(4, opd.getAmountOutput());
                pstmt.setString(5, opd.getIDPro());
                pstmt.setDouble(6, opd.getPrice());
            return pstmt.executeUpdate() > 0;
        }

    }
    public boolean insertOut(output out) throws Exception {
        String sql = "INSERT INTO output( IDOutput ,DateInvoice,OrderID)"
                + "values(?,?,?)";
        try (
            Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, out.getIDOutput());
            pstmt.setString(2, out.getDateInvoice());
            pstmt.setString(3, out.getOrderID());
            return pstmt.executeUpdate() > 0;
        }
    }
    public boolean updateOrdDetail(output_detail opd) throws Exception{
       String sql = "update output_detail set OutputID=?, WareHouseID=?, AmountOutput=?, IDPro=?, Price=? where ID=?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);
            ) {
               pstmt.setString(6, opd.getID());
                pstmt.setString(1, opd.getOutputID());
                pstmt.setString(2, opd.getWareHouseID());
                pstmt.setInt(3, opd.getAmountOutput());
                pstmt.setString(4, opd.getIDPro());
                pstmt.setDouble(5, opd.getPrice());
                
            return pstmt.executeUpdate() > 0;
        }
   }
}
