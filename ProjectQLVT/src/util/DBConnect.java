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
                PreparedStatement ps = conn.prepareStatement("select * from owe");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    list.add(new owe(rs.getString("IdOwe"), rs.getString("NameCus"),rs.getString("PhoneCus"),rs.getString("EmailCus"),rs.getString("AddressCus"), rs.getString("PaymentForm"),rs.getString("status")));
                }
            } catch (Exception e) {
            }
            return list;
        }

}
