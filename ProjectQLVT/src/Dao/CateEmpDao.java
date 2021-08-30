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
import model.catemp;
import util.DBConnect;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author thanh
 */
public class CateEmpDao {
    public catemp findByName(String EmpCate)
            throws Exception{
        String sql= "select* from catemp where EmpCate= '"+EmpCate+"'";
        try(
            Connection con= DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
            try(ResultSet rs= pstmt.executeQuery()){
                while(rs.next()){
                    catemp cam = new catemp();
                    cam.setID(rs.getString("ID"));
                    cam.setEmpCate(rs.getString("EmpCate"));
                    return cam;
                }
            }
            
        }
        return null;
    }
}
