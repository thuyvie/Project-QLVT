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
import model.catepro;
import util.DBConnect;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Mun Chan
 */
public class CateProDao {
     public boolean insert(catepro cp)
            throws Exception {
        String sql = "INSERT INTO cateproduct( NameCate)"
                + "values(?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
//                pstmt.setInt(1, cp.getID());
                pstmt.setString(1, cp.getNameCate());
            return pstmt.executeUpdate() > 0;
        }

    }
    public boolean update(catepro cp)
            throws Exception {
        String sql = "update cateproduct"
                + " set NameCate=?" 
                + " where ID= ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                 pstmt.setString(2, cp.getID());
                 pstmt.setString(1, cp.getNameCate());
            return pstmt.executeUpdate() > 0;
        }
    }
    public catepro findByName(String NameCate)
            throws Exception{
        String sql= "select * from cateproduct where NameCate= '"+NameCate+"'";
        try(
            Connection con= DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
            try(ResultSet rs= pstmt.executeQuery()){
                while(rs.next()){
                    catepro cp = new catepro();
                    cp.setID(rs.getString("ID"));
                    cp.setNameCate(rs.getString("NameCate"));
                    return cp;
                }
            }
            
        }
        return null;
    }
    public ObservableList<catepro> findAll() {
        ObservableList<catepro> listcp = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT * FROM cateproduct ORDER BY ID DESC";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
               catepro cp = new catepro();
                    cp.setID(rs.getString("ID"));
                    cp.setNameCate(rs.getString("NameCate"));
                listcp.add(cp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listcp;
    }
}
