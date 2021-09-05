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
import model.InTM;
import model.InTM2;
import model.customer;
import model.dtmTM;
import model.input;
import model.input2;
import model.inputdetail;
import model.orderdetail;
import model.warehouse;
import model.wh;
import util.DBConnect;

/**
 *
 * @author Mun Chan
 */
public class inputDao {

    public boolean insertIn(input2 in2) throws Exception {
        String sql = "INSERT INTO input( InputID, DateInvoice, Total)"
                + "values(?,?,?)";
        try (
                 Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, in2.getInputID());
            pstmt.setString(2, in2.getDate());
            pstmt.setDouble(3, in2.getTotal());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean insertInDetail(inputdetail in) throws Exception {
        String sql = "INSERT INTO input_detail( ID,Amount,Price,InputID,IDProduct)"
                + "values(?,?,?,?,?)";
        try (
                 Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, in.getID());
            pstmt.setDouble(2, in.getAmount());
            pstmt.setDouble(3, in.getPrice());
            pstmt.setString(4, in.getInputID());
            pstmt.setString(5, in.getIDProduct());
            return pstmt.executeUpdate() > 0;
        }
    }

    public static String getRandomNumberString() {
        {
            Random rand = new Random();
            int rand_int1 = rand.nextInt(100000);
            return String.valueOf(rand_int1);
        }
    }

    public boolean insertWareHouse(wh ware) throws Exception {
        String sql = "INSERT INTO warehouse( ID, ProductID, Inventory, Amountinput, Dateinput, OriginalPrice, IDInput)"
                + "values(?,?,?,?,?,?,?)";
        try (
                 Connection con = DBConnect.getConnect();  PreparedStatement pstmt = con.prepareStatement(sql);) {
            pstmt.setString(1, ware.getID());
            pstmt.setString(2, ware.getProductID());
            pstmt.setInt(3, ware.getInventory());
            pstmt.setInt(4, ware.getAmountinput());
            pstmt.setString(5, ware.getDateinput());
            pstmt.setDouble(6, ware.getPrice());
            pstmt.setString(7, ware.getIDInput());
            return pstmt.executeUpdate() > 0;
        }
    }

    private boolean addinDetail(input in) throws SQLException, ClassNotFoundException, Exception {
        for (InTM ord : in.getAllInDetail()) {
            inputdetail orderTable = new inputdetail(getRandomNumberString(), ord.getAmount(), ord.getPrice(), in.getInputID(), ord.getIDProduct());
            System.out.println("");
            boolean isAddedOrderDetails = insertInDetail(orderTable);
            if (!isAddedOrderDetails) {
                return false;
            }
        }
        return true;
    }

    private boolean addware(input in) throws SQLException, ClassNotFoundException, Exception {
        for (InTM ord2 : in.getAllInDetail()) {
            wh orderTable2 = new wh(getRandomNumberString(), ord2.getIDProduct(), ord2.getAmount(), ord2.getAmount(), in.getDate(), ord2.getPrice(), in.getInputID());
            System.out.println("");
            boolean isAddedOrderDetails = insertWareHouse(orderTable2);
            if (!isAddedOrderDetails) {
                return false;
            }
        }
        return true;
    }
    private boolean addin(input in) throws SQLException, ClassNotFoundException, Exception {
        for (InTM ord2 : in.getAllInDetail()) {
            input2 orderTable2 = new input2(in.getInputID(),in.getDate(),in.getTotal());
            System.out.println("");
            boolean isAddedOrderDetails = insertIn(orderTable2);
            if (!isAddedOrderDetails) {
                return false;
            }
        }
        return true;
    }

    public boolean placeOrder(input i) throws Exception {
        Connection con = DBConnect.getConnect();
        con.setAutoCommit(false);
        input2 in2 = new input2(i.getInputID(), i.getDate(), i.getTotal());
        boolean add = insertIn(in2);
        try {
            if (add) {
                boolean inputDetailAdd = insertInDetail(
                        new inputdetail(getRandomNumberString(), i.getAmount(), i.getPrice(), i.getInputID(), i.getIDProduct())
                );
                if (inputDetailAdd) {
                    boolean ispayAdd = insertWareHouse(
                            new wh(getRandomNumberString(), i.getProductID(), i.getInventory(), i.getAmountinput(), i.getDateinput(), i.getOriginalPrice(), i.getInputID())
                    );
                }
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } finally {
            con.setAutoCommit(true);
        }
    }

    public boolean placeOrder2(input i) throws Exception {
        Connection con = DBConnect.getConnect();
        con.setAutoCommit(false);
        input2 in2 = new input2(i.getInputID(), i.getDate(), i.getTotal());
        boolean add = insertIn(in2);
//        boolean add = addin(i);
        try {
            if (add) {
                boolean inputDetailAdd = addinDetail(i);
                if (inputDetailAdd) {
                    boolean ispayAdd = addware(i);
                }
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } finally {
            con.setAutoCommit(true);
        }
    }

   
}
