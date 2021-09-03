/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.productDao;
import Dao.wareDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Duration;
import model.catepro;
import model.product;
import model.vendorlot;
import model.warehouse;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.DBConnect;

/**
 * FXML Controller class
 *
 * @author thanh
 */
public class WareHouseController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<warehouse> tableproductview;
    @FXML
    private TableColumn<warehouse, String> tblidpro;
    @FXML
    private TableColumn<warehouse, Integer> tblinventory;
    @FXML
    private TableColumn<warehouse, Integer> tblamount;
    @FXML
    private TableColumn<warehouse, Double> tblprice;
    @FXML
    private TableColumn<warehouse, String> tbldelete;
    @FXML
    private TextField txtinventory;
    @FXML
    private TextField txtprice;
    @FXML
    private TextField txtamount;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btninsert;
    @FXML
    private JFXButton btnup;
    @FXML
    private JFXButton btnclear;
    @FXML
    private Label txtdate;
    @FXML
    private JFXComboBox<String> cbb;
    @FXML
    private Label txtd;
    String query = null;
    warehouse ware = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    productDao proDao = new productDao();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillCBB();
        setid();
        generateDateTime();
        showWare();
    } 
    public static String getRandomNumberString() {
        {
            Random rand = new Random();
            int rand_int1 = rand.nextInt(100000);
            return String.valueOf(rand_int1);
        }
    }
     public void generateDateTime() {
        this.txtdate.setText(LocalDate.now().toString());
     }    
    public void setid(){
        try {
            txtd.setText(getRandomNumberString());
        } catch (Exception e) {
        }
    }
     public ObservableList<warehouse> findAll() {
        ObservableList<warehouse> listpro = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT  warehouse.ID, warehouse.ProductID,product.itemCode,warehouse.Inventory,warehouse.Amountinput,warehouse.Dateinput,warehouse.OriginalPrice FROM warehouse INNER JOIN product ON  warehouse.ProductID= product.itemCode ORDER BY ID DESC";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                warehouse pro = new warehouse();
                pro.setID(rs.getString("ID"));
                pro.setProductID(rs.getString("ProductID"));
                pro.setItemCode(rs.getString("itemCode"));
                pro.setInventory(rs.getString("Inventory"));
                pro.setAmountInput(rs.getString("AmountInput"));
                pro.setDateInput(rs.getString("DateInput"));
                pro.setOriginalPrice(rs.getDouble("OriginalPrice"));
                listpro.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listpro;
    }
     public void showWare(){
         ObservableList<warehouse> list = findAll();
        tblidpro.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        tblinventory.setCellValueFactory(new PropertyValueFactory<>("Inventory"));
        tblamount.setCellValueFactory(new PropertyValueFactory<>("AmountInput"));
        tblprice.setCellValueFactory(new PropertyValueFactory<>("OriginalPrice"));
        Callback<TableColumn<warehouse, String>, TableCell<warehouse, String>> cellFoctory = (TableColumn<warehouse, String> param) -> {
            final TableCell<warehouse, String> cell = new TableCell<warehouse, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:15px;"
                                + "-fx-fill:#ff1744;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                ware= tableproductview.getSelectionModel().getSelectedItem();
                                query = "delete from warehouse where ID='" + ware.getID() + "'";
                                connection = DBConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                showWare();
                            } catch (SQLException ex) {
                                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }
            };
            return cell;
        };
        tbldelete.setCellFactory(cellFoctory);
        tableproductview.setItems(list);
     }
    public void fillCBB() {
        ResultSet rs;
        try {
            ObservableList<String> listven = FXCollections.observableArrayList();
            String sql = "SELECT itemCode FROM product";
            Connection con = DBConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listven.add(rs.getString("itemCode"));
            }
            cbb.setItems(listven);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void clickTable(MouseEvent event) {
        warehouse p = tableproductview.getSelectionModel().getSelectedItem();
        txtinventory.setText(p.getInventory());
        txtamount.setText(p.getAmountInput());
        cbb.setValue(p.getItemCode());
        txtprice.setText(String.valueOf(p.getOriginalPrice()));
        txtd.setText(p.getID());
    }

    @FXML
    private void SearchAction(ActionEvent event) {
         try {
            wareDao proDao = new wareDao();
            if (cbb.getValue().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Key Word ");
                alert.showAndWait();
            } else{ 
                warehouse ware = proDao.searchPro(cbb.getValue());
                if (ware != null) {
                txtd.setText(ware.getID());
                txtinventory.setText(ware.getInventory());
                cbb.setValue(ware.getProductID());
                txtprice.setText(String.valueOf(ware.getOriginalPrice()));
                txtamount.setText(ware.getAmountInput());
                txtdate.setText(ware.getDateInput());
                String tilte = "ProductID Searched ";
                String message = "ProductID Is " + "" + cbb.getValue() + "";
                setid();
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            } else {
                String tilte = "Searched ProductID Not Found";
                String message = "Try Again";
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(3000));
            }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void insertAction(ActionEvent event) throws Exception {
        try {
            warehouse ware = new warehouse();
            ware.setID(txtd.getText());
//            product pro = proDao.searchProduct(cbb.getValue());
            ware.setProductID(cbb.getValue());
            ware.setInventory(txtinventory.getText());
            ware.setAmountInput(txtamount.getText());
            ware.setOriginalPrice(Double.parseDouble(txtprice.getText()));
            ware.setDateInput(txtdate.getText());
//            ware.setPro(pro);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            wareDao war = new wareDao();
            if (war.insert(ware)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "WareHouse Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "WareHouse Is Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showWare();
                setid();

            } else {
                (new Alert(Alert.AlertType.ERROR, "WareHouse Not Added ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "WareHouse Is Not Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        showWare();
    }

    @FXML
    private void UpdateAction(ActionEvent event) throws Exception {
          try {
             warehouse ware = new warehouse();
            ware.setID(txtd.getText());
//            product pro = proDao.searchProduct(cbb.getValue());
            ware.setProductID(cbb.getValue());
            ware.setInventory(txtinventory.getText());
            ware.setAmountInput(txtamount.getText());
            ware.setOriginalPrice(Double.parseDouble(txtprice.getText()));
            ware.setDateInput(txtdate.getText());
//            ware.setPro(pro);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            wareDao war = new wareDao();
            if (war.update(ware)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "WareHouse Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "WareHouse Is Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showWare();
                setid();
                

            } else {
                (new Alert(Alert.AlertType.ERROR, "WareHouse Not Updated ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Updated Un Successful";
                message = "WareHouse Is Not Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        showWare();
    }

    @FXML
    private void ClearAction(ActionEvent event) {
        txtinventory.setText(null);
        txtamount.setText(null);
        txtprice.setText(null);
    }
    
}
