/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlvt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static qlvt.test.mua;
import static qlvt.test.pst1;
import static qlvt.test.rs1;
import util.DBConnect;

/**
 *
 * @author thanh
 */
public class test2 {

    public static ResultSet rs1 = null, rs2 = null, rs3 = null, rs4 = null, rs5 = null;
    public static PreparedStatement pst1 = null, pst2 = null, pst3 = null, pst4 = null, pst5 = null;
    public static Integer kho, mua, thieu, du, spkho, inventory,amountip,tensp;
    public static String sql1, sql2, sql3, sql4, sql5;

    public static void main(String[] args) throws SQLException {
        Connection con = DBConnect.getConnect();
        String sql1 = "SELECT warehouse.ProductID,product.namepro,SUM(warehouse.Inventory) AS 'Inventory',SUM(warehouse.Amountinput) AS 'AmountInput'\n" +
"FROM warehouse ,product\n" +
"WHERE warehouse.ProductID=product.itemCode\n" +
"GROUP BY warehouse.ProductID";
        pst1 = con.prepareStatement(sql1);
        rs1 = pst1.executeQuery();
        while (rs1.next()) {
            inventory = rs1.getInt("Inventory");
            amountip = rs1.getInt("Amountinput");
            String tensp = rs1.getString("namepro");
        }
        System.out.println("tên sp: "+ tensp +", so luong nhap: "+amountip +", ton kho: "+inventory );

    }
}
