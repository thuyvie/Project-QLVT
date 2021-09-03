package Dao;


import model.emp;
import util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mun Chan
 */
public class logDao {
     public emp validate(String userName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Account, Password FROM emp WHERE Account=?";
        ResultSet resultSet = CrudUtil.executeQuery(sql, userName);
        if(resultSet.next()){
            return new emp(resultSet.getString(1),resultSet.getString(2));
        }
        return null;
    }
     public emp getValidate(String userName) throws SQLException, ClassNotFoundException {
         emp e = validate(userName);
         return new emp(e.getAccount(), e.getPassword());
     }
}
