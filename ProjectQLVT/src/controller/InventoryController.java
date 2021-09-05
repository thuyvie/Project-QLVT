/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.inputDao;
import Dao.orderDao;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.customer;
import model.ord;
import model.product;
import model.wh;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.DBConnect;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class InventoryController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<wh> tblinven;
    @FXML
    private TableColumn<wh, String> tblid;
    @FXML
    private TableColumn<wh, String> tblitem;
    @FXML
    private TableColumn<wh, Integer> tblinventory;
    @FXML
    private TableColumn<wh, Integer> tblamount;
    @FXML
    private TableColumn<wh, String> tbldate;
    @FXML
    private TableColumn<wh, String> tblinput;
    @FXML
    private TextField txttimkiem;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private Label txtinven;
    @FXML
    private Label txtamount;
    wh Wh = null;
    ObservableList<wh> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
            showIn();
            search();
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

    public ObservableList<wh> findAll() {
        ObservableList<wh> listowe = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        int sum = 0;
        try {
            String sql = ("SELECT warehouse.ID, warehouse.ProductID,SUM(Inventory) AS 'Inventory',SUM(Amountinput) AS 'Amountinput',warehouse.Dateinput,warehouse.IDInput FROM warehouse INNER JOIN input ON warehouse.IDInput = input.InputID GROUP BY ProductID ORDER BY ID DESC");
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                wh w = new wh();
                w.setID(rs.getString("ID"));
                w.setProductID(rs.getString("ProductID"));
                w.setInventory(rs.getInt("Inventory"));
                w.setAmountinput(rs.getInt("Amountinput"));
                w.setDateinput(rs.getString("Dateinput"));
                w.setIDInput(rs.getString("IDInput"));
                listowe.add(w);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listowe;
    }// chay lai thu ddc chua, oke, cam on m

    public void showIn() {
        ObservableList<wh> list = findAll();
        tblid.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tblitem.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        tblinventory.setCellValueFactory(new PropertyValueFactory<>("Inventory"));
        tblamount.setCellValueFactory(new PropertyValueFactory<>("Amountinput"));
        tbldate.setCellValueFactory(new PropertyValueFactory<>("Dateinput"));
        tblinput.setCellValueFactory(new PropertyValueFactory<>("IDInput"));
        tblinven.setItems(list);
    }

    public void search() {
        tblid.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tblitem.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        tblinventory.setCellValueFactory(new PropertyValueFactory<>("Inventory"));
        tblamount.setCellValueFactory(new PropertyValueFactory<>("Amountinput"));
        tbldate.setCellValueFactory(new PropertyValueFactory<>("Dateinput"));
        tblinput.setCellValueFactory(new PropertyValueFactory<>("IDInput"));
        data = DBConnect.getWare();
        tblinven.setItems(data);
        FilteredList<wh> filteredData = new FilteredList<>(data, e -> true);
        txttimkiem.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super wh>) o -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (o.getNamepro().contains(newValue)) {
                    return true;
                } else if (o.getProductID().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<wh> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblinven.comparatorProperty());
        tblinven.setItems(sortedData);
    }
     private void paid() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnect.getConnect().
                prepareStatement("SELECT   SUM(Inventory)  FROM warehouse")
                .executeQuery();
        if (set.next()) {
            int customerCount = set.getInt(1);
            txtinven.setText(String.valueOf(customerCount));
        }
        

    }
     private void owe() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnect.getConnect().
                prepareStatement("SELECT   SUM(Amountinput)  FROM warehouse")
                .executeQuery();
        if (set.next()) {
            int customerCount = set.getInt(1);
            txtamount.setText(String.valueOf(customerCount));
        }

    }
    @FXML
    private void ClickAction(MouseEvent event) {
//        wh w = tblinven.getSelectionModel().getSelectedItem();
//        txtinven.setText(String.valueOf(w.getInventory()));
//        txtamount.setText(String.valueOf(w.getAmountinput()));
    }

    @FXML
    private void SearchAction(ActionEvent event) {
    }

}
