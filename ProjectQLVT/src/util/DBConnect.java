/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.owe;
import net.sf.jasperreports.engine.JRException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.ord;
import model.product;
/**
 *
 * @author Mun Chan
 */
public class DBConnect {
      private static String HOST = "127.0.0.1";
        private static int PORT = 3306;
        private static String DB_NAME = "qlvt";
        private static String USERNAME = "root";
        private static String PASSWORD = "";
        private static Connection connection ;
        
        
        public static Connection getConnect (){
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
            System.out.println("connect");
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("unconect");
        }
            
            return  connection;
        }
        public static ObservableList<owe> getOwe(){
            Connection conn = getConnect();
            ObservableList<owe> list = FXCollections.observableArrayList();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT detailowe.NameCus, detailowe.PhoneCus,detailowe.Paid, detailowe.Owe, detailowe.TotalDebt, detailowe.IdOwe, owe.EmailCus, owe.AddressCus,owe.PaymentForm,owe.status FROM detailowe INNER JOIN owe ON detailowe.IdOwe = owe.IdOwe ORDER BY IdOwe DESC");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    list.add(new owe(rs.getString("IdOwe"), rs.getString("NameCus"),rs.getString("PhoneCus"),rs.getString("EmailCus"),rs.getString("AddressCus"), rs.getString("PaymentForm"),rs.getString("status"),rs.getDouble("Paid"),rs.getDouble("Owe"),rs.getDouble("TotalDebt")));
                }
            } catch (Exception e) {
            }
            return list;
        }
        
        public static ObservableList<ord> getOrd(){
            Connection conn = getConnect();
            ObservableList<ord> list = FXCollections.observableArrayList();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT order_detail.OrderID,orders.OrdID, orders.NameCus, orders.PhoneCus, orders.EmailCus,"
                        + "orders.AddressCus, orders.dateOrd, orders.timeOrd, order_detail.IDProduct, product.itemCode, product.namepro,"
                        + "order_detail.qty, order_detail.Price, order_detail.Total FROM order_detail INNER JOIN orders ON order_detail.OrderID = orders.OrdID INNER JOIN product ON order_detail.IDProduct = product.itemCode ORDER BY OrderID DESC");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    list.add(new ord(rs.getString("OrdID"),rs.getString("NameCus"),rs.getString("PhoneCus"),rs.getString("EmailCus"),rs.getString("AddressCus"), rs.getString("dateOrd"),rs.getString("timeOrd"),rs.getString("itemCode"),rs.getString("namepro"),rs.getInt("qty"), rs.getDouble("Price"), rs.getDouble("Total")));
                }
            } catch (Exception e) {
            }
            return list;
        }
        
        public static ObservableList<product> getProduct(){
            Connection conn = getConnect();
            ObservableList<product> list = FXCollections.observableArrayList();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT product.itemCode, product.namepro, product.vendorid, vendor.vendorname, product.description, product.size, product.price, product.qty, product.batchid, product.IDCate, cateproduct.NameCate FROM product INNER JOIN vendor ON product.vendorid = vendor.vendorID INNER JOIN cateproduct ON product.IDCate = cateproduct.ID ORDER BY itemCode DESC");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    list.add(new product(rs.getString("itemCode"), rs.getString("namepro"),rs.getString("vendorname"),rs.getString("description"), rs.getString("size"),rs.getString("batchid"),rs.getString("NameCate")));
                }
            } catch (Exception e) {
            }
            return list;
        }

}
