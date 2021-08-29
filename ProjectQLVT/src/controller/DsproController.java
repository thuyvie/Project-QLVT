/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.ord;
import model.product;
import util.DBConnect;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class DsproController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<product> tblds;
    @FXML
    private TableColumn<product, String> tblname;
    @FXML
    private TableColumn<product, String> tblphone;
    @FXML
    private TableColumn<product, String> tblemail;
    @FXML
    private TableColumn<product, String> tbladdress;
    @FXML
    private TableColumn<product, String> tblform;
    @FXML
    private TableColumn<product, String> tblstt;
    @FXML
    private TableColumn<product, String> tblcode;
    @FXML
    private TextField txttimkiem;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private TextField txtcodepro;
    product pro = null;
    ObservableList<product> data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPro();
        search();
    }    
     public ObservableList<product> findAll() {
        ObservableList<product> listpro = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT product.itemCode, product.namepro, product.vendorid, vendor.vendorname, product.description, product.size, product.price, product.qty, product.batchid, product.IDCate, cateproduct.NameCate FROM product INNER JOIN vendor ON product.vendorid = vendor.vendorID INNER JOIN cateproduct ON product.IDCate = cateproduct.ID ORDER BY itemCode DESC";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                product pro = new product();
                pro.setItemCode(rs.getString("itemCode"));
                pro.setNamepro(rs.getString("namepro"));
                pro.setVendorid(rs.getString("vendorid"));
                pro.setVendorname(rs.getString("vendorname"));
                pro.setDescription(rs.getString("description"));
                pro.setSize(rs.getString("size"));
                pro.setPrice(rs.getDouble("price"));
                pro.setQty(rs.getInt("qty"));
                pro.setBatchid(rs.getString("batchid"));
                pro.setIDCate(rs.getInt("IDCate"));
                pro.setNameCate(rs.getString("NameCate"));
                listpro.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listpro;
    }
     public void showPro(){
        ObservableList<product> list = findAll();
        tblname.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("namepro"));
        tblemail.setCellValueFactory(new PropertyValueFactory<>("vendorname"));
        tbladdress.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblform.setCellValueFactory(new PropertyValueFactory<>("size"));
        tblstt.setCellValueFactory(new PropertyValueFactory<>("batchid"));
        tblcode.setCellValueFactory(new PropertyValueFactory<>("NameCate"));
        tblds.setItems(list);
     }
     public void search(){
        tblname.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("namepro"));
        tblemail.setCellValueFactory(new PropertyValueFactory<>("vendorname"));
        tbladdress.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblform.setCellValueFactory(new PropertyValueFactory<>("size"));
        tblstt.setCellValueFactory(new PropertyValueFactory<>("batchid"));
        tblcode.setCellValueFactory(new PropertyValueFactory<>("NameCate"));
        data = DBConnect.getProduct();
        tblds.setItems(data);
        FilteredList<product> filteredData = new FilteredList<>(data, e -> true);
        txttimkiem.textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate((Predicate<? super product>) o -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (o.getNamepro().contains(newValue)) {
                    return true;
                } 
                return false;
            });
        });
        SortedList<product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tblds.comparatorProperty());
        tblds.setItems(sortedData);
     }
    @FXML
    private void ClickAction(MouseEvent event) {
        product pro = tblds.getSelectionModel().getSelectedItem();
        txtcodepro.setText(pro.getItemCode());
    }

    @FXML
    private void SearchAction(ActionEvent event) {
    }
    
}
