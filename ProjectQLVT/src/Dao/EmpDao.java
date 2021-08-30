/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.emp;
import util.DBConnect;
import java.sql.PreparedStatement;
import model.catemp;
/**
 *
 * @author thanh
 */
public class EmpDao {
    public boolean insert(emp emp)throws Exception {
        String sql = "INSERT INTO product( ID,NameEmp,PhoneEmp,Account, Password, Salary,IDCateEmp)"
                + "values(?,?,?,?,?,?)";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(1, emp.getID());
                pstmt.setString(2, emp.getNameEmp());
                pstmt.setString(3, emp.getPhoneEmp());
                pstmt.setString(4, emp.getAccount());
                pstmt.setString(5, emp.getPassword());
                pstmt.setDouble(6, emp.getSalary());
                pstmt.setString(7, emp.getCate().getID());
            return pstmt.executeUpdate() > 0;
        }

    }
     public boolean update(emp emp)
            throws Exception {
        String sql = "update emp"
                + " set NameEmp =?, PhoneEmp =?, Account = ?,Password=?, Salary = ?,IDCateEmp =?"
                + " where ID = ?";
        try (
                Connection con = DBConnect.getConnect();
                PreparedStatement pstmt = con.prepareStatement(sql);) {
                pstmt.setString(7, emp.getID());
                pstmt.setString(1, emp.getNameEmp());
                pstmt.setString(2, emp.getPhoneEmp());
                pstmt.setString(3, emp.getAccount());
                pstmt.setString(4, emp.getPassword());
                pstmt.setDouble(4, emp.getSalary());
                pstmt.setString(6, emp.getCate().getID());
                
            return pstmt.executeUpdate() > 0;
        }
    }
     public emp searchEmp(String NameEmp) {
        ObservableList<emp> listproduct = FXCollections.observableArrayList();
         String sql = "SELECT emp.ID, emp.NameEmp, emp.Phone, emp.Account, emp.Password, emp.Salary, emp.IDCateEmp, catemp.EmpCate FROM emp INNER JOIN catemp ON emp.IDCateEmp = catemp.ID where NameEmp='" + NameEmp + "'";
        Statement stmt;
        try (
            Connection con = DBConnect.getConnect();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ){
             try(ResultSet rs= pstmt.executeQuery()){         
                while (rs.next()) {
                emp emp = new emp();
                emp.setID(rs.getString("ID"));
                emp.setNameEmp(rs.getString("NameEmp"));
                emp.setPhoneEmp(rs.getString("Phone"));
                emp.setAccount(rs.getString("Account"));
                emp.setPassword(rs.getString("Password"));
                emp.setSalary(rs.getDouble("Salary"));
                emp.setCate(new catemp(rs.getString("ID"), rs.getString("EmpCate")));
                return emp;
                }
             }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;

    }
    
}
