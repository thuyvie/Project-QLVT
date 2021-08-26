/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.detailOwe;
import model.dtm;
import model.order2;
import model.owe;
import model.owe2;
import model.owedetail;
import model.payment;
import util.DBConnect;

/**
 *
 * @author Mun Chan
 */
public class payowe {

    public boolean insert(owe2 o2) throws Exception {
        String sql = "INSERT INTO owe( IdOwe, NameCus, PhoneCus,EmailCus,AddressCus, PaymentForm, status)"
                + "values(?,?,?,?,?,?,?)";
        try (
                 Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, o2.getIdOwe());
            pstmt.setString(2, o2.getNameCus());
            pstmt.setString(3, o2.getPhoneCus());
            pstmt.setString(4, o2.getEmailCus());
            pstmt.setString(5, o2.getAddressCus());
            pstmt.setString(6, o2.getPaymentForm());
            pstmt.setString(7, o2.getStatus());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean insertOweDetail(detailOwe od) throws Exception {
        String sql = "INSERT INTO detailowe( ID, NameCus, PhoneCus,Paid,Owe, TotalDebt,IdOwe)"
                + "values(?,?,?,?,?,?,?)";
        try (
                 Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, od.getID());
            pstmt.setString(2, od.getNameCus());
            pstmt.setString(3, od.getPhoneCus());
            pstmt.setDouble(4, od.getPaid());
            pstmt.setDouble(5, od.getOwe());
            pstmt.setDouble(6, od.getTotalDebt());
            pstmt.setString(7, od.getIdOwe());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean insertOweDetail2(owedetail od) throws Exception {
        String sql = "INSERT INTO detailowe( ID, NameCus, PhoneCus,Paid,Owe, TotalDebt,IdOwe)"
                + "values(?,?,?,?,?,?,?)";
        try (
                 Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, getRandomNumberString());
            pstmt.setString(2, od.getNameCus());
            pstmt.setString(3, od.getPhoneCus());
            pstmt.setString(4, od.getPaid());
            pstmt.setString(5, od.getOwe());
            pstmt.setString(6, od.getTotalDebt());
            pstmt.setString(7, od.getIdOwe());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean updateOwe(owe2 o2) throws Exception {
        String sql = "UPDATE owe SET NameCus=?, PhoneCus=?, status=?"
                + "WHERE IdOwe=?";
        try (
                 Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(4, o2.getIdOwe());
            pstmt.setString(1, o2.getNameCus());
            pstmt.setString(2, o2.getPhoneCus());
            pstmt.setString(3, o2.getStatus());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean placeOrder2(owe o) throws Exception {
        Connection con = DBConnect.getConnect();
        con.setAutoCommit(false);
        owe2 ow2 = new owe2(o.getIdOwe(), o.getNameCus(), o.getPhoneCus(), o.getStatus());
        boolean add = updateOwe(ow2);
        try {
            if (add) {
                boolean ispayoweAdd = insertOweDetail(
                        new detailOwe(getRandomNumberString(), o.getNameCus(), o.getPhoneCus(), o.getPaid(), o.getOwe(), o.getTotalDebt(), o.getIdOwe())
                );

            }
            con.commit();
            return true;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public static String getRandomNumberString() {
        {
            Random rand = new Random();
            int rand_int1 = rand.nextInt(100000);
            return String.valueOf(rand_int1);
        }
    }

    public boolean placeOrder(owe o) throws Exception {
        Connection con = DBConnect.getConnect();
        con.setAutoCommit(false);
        owe2 ow2 = new owe2(o.getIdOwe(), o.getNameCus(), o.getPhoneCus(), o.getEmailCus(), o.getAddressCus(), o.getPaymentForm(), o.getStatus());
        boolean add = insert(ow2);
        try {
            if (add) {
                boolean ispayoweAdd = insertOweDetail(
                        new detailOwe(getRandomNumberString(), o.getNameCus(), o.getPhoneCus(), o.getPaid(), o.getOwe(), o.getTotalDebt(), o.getIdOwe())
                );

            }
            con.commit();
            return true;
        } finally {
            con.setAutoCommit(true);
        }
    }

    public order2 searchCus(String PhoneCus) {
        ObservableList<order2> listord = FXCollections.observableArrayList();
        String sql = "SELECT OrdID,NameCus, PhoneCus,AddressCus,EmailCus FROM  orders  where PhoneCus='" + PhoneCus + "'";
        Statement stmt;
        try (
                 Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    order2 ord = new order2();
                    ord.setOrdID(rs.getString("OrdID"));
                    ord.setNameCus(rs.getString("NameCus"));
                    ord.setPhoneCus(rs.getString("PhoneCus"));
                    ord.setAddressCus(rs.getString("AddressCus"));
                    ord.setEmailCus(rs.getString("EmailCus"));
                    return ord;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public payment searchPay(String OrdID) {
        ObservableList<payment> listpay = FXCollections.observableArrayList();
        String sql = "SELECT NameCus,amount,note FROM  payment  where OrdID='" + OrdID + "'";
        Statement stmt;
        try (
                 Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            try ( ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    payment pay = new payment();
                    pay.setNameCus(rs.getString("NameCus"));
                    pay.setAmount(rs.getDouble("amount"));
                    pay.setNote(rs.getString("note"));
                    return pay;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
