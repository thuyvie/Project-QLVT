/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.detailOwe;
import util.DBConnect;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class DThuController implements Initializable {

    @FXML
    private Label totalmoney;
    @FXML
    private Label totalowe;
    @FXML
    private TableView<detailOwe> tbltotal;
    @FXML
    private TableColumn<detailOwe, String> tblname;
    @FXML
    private TableColumn<detailOwe, String> tblphone;
    @FXML
    private TableColumn<detailOwe, Double> tblowe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showO();
        try {
            paid();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try {
            owe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<detailOwe> findAll() {
        ObservableList<detailOwe> listowe = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT detailowe.NameCus, detailowe.PhoneCus, detailowe.TotalDebt FROM detailowe ORDER BY TotalDebt DESC";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                detailOwe ow = new detailOwe();
                ow.setNameCus(rs.getString("NameCus"));
                ow.setPhoneCus(rs.getString("PhoneCus"));
                ow.setTotalDebt(rs.getDouble("TotalDebt"));
                listowe.add(ow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listowe;
    }

    public void showO() {
        ObservableList<detailOwe> list = findAll();
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblowe.setCellValueFactory(new PropertyValueFactory<>("TotalDebt"));
        tbltotal.setItems(list);
    }
        private void paid() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnect.getConnect().
                prepareStatement
                        ("SELECT   SUM(Paid) SalesQuantity FROM detailowe")
                .executeQuery();
        if (set.next()) {
            int customerCount = set.getInt(1);
            totalmoney.setText(String.valueOf(customerCount));
        }

    }
        private void owe() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnect.getConnect().
                prepareStatement
                        ("SELECT   SUM(TotalDebt) SalesQuantity FROM detailowe")
                .executeQuery();
        if (set.next()) {
            int customerCount = set.getInt(1);
            totalowe.setText(String.valueOf(customerCount));
        }

    }
}
