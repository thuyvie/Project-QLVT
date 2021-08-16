/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.productDao;
import Dao.vendorDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.product;
import util.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.HBox;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.vendorlot;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.DBConnect;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class ProductController implements Initializable {

    @FXML
    private TableView<product> tableproductview;
    @FXML
    private TableColumn<product, String> tblname;
    @FXML
    private TableColumn<product, String> tblvendor;
    @FXML
    private TableColumn<product, String> tbldes;
    @FXML
    private TableColumn<product, String> tblsize;
    @FXML
    private TableColumn<product, Double> tblprice;
    @FXML
    private TableColumn<product, Integer> tblqty;
    @FXML
    private TableColumn<product, String> tblbatch;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtdes;
    @FXML
    private TextField txtprice;
    @FXML
    private TextField txtbatch;
    @FXML
    private TextField txtsize;
    @FXML
    private TextField txtqty;
    @FXML
    private JFXComboBox<String> cbb;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btninsert;

    /**
     * Initializes the controller class.
     */
    PreparedStatement preparedStatement = null;
    String query = null;
    product products = null;
    Connection connection = null;
    private boolean Save;
    private boolean isUpdate;
    vendorDao venDao = new vendorDao();
    @FXML
    private TableColumn<product, String> tbldelete;
    @FXML
    private TextField txtcodepro;
    private JFXButton btnupdate1;
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton btnup;
    @FXML
    private JFXButton btnclear;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPro();
        fillCBB();
    }

    public ObservableList<product> findAll() {
        ObservableList<product> listpro = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT product.itemCode, product.namepro, product.vendorid, vendor.vendorname, product.description, product.size, product.price, product.qty, product.batchid FROM product INNER JOIN vendor ON product.vendorid = vendor.vendorID ORDER BY itemCode DESC";
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
                listpro.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listpro;
    }

    public void showPro() {
        ObservableList<product> list = findAll();
        tblname.setCellValueFactory(new PropertyValueFactory<>("namepro"));
        tblvendor.setCellValueFactory(new PropertyValueFactory<>("vendorname"));
        tbldes.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblsize.setCellValueFactory(new PropertyValueFactory<>("size"));
        tblprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tblqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblbatch.setCellValueFactory(new PropertyValueFactory<>("batchid"));
        Callback<TableColumn<product, String>, TableCell<product, String>> cellFoctory = (TableColumn<product, String> param) -> {
            final TableCell<product, String> cell = new TableCell<product, String>() {

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
                                products = tableproductview.getSelectionModel().getSelectedItem();
                                query = "delete from product where itemCode='" + products.getItemCode() + "'";
                                connection = DBConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                showPro();
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

    private void ClearText() {
        txtcodepro.setText(null);
        txtname.setText(null);
        cbb.getItems();
        txtdes.setText(null);
        txtsize.setText(null);
        txtprice.setText(null);
        txtqty.setText(null);
        txtbatch.setText(null);
    }

    public void fillCBB() {
        ResultSet rs;
        try {
            ObservableList<String> listven = FXCollections.observableArrayList();
            String sql = "SELECT vendorname FROM vendor";
            Connection con = DBConnect.getConnect();
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listven.add(rs.getString("vendorname"));
            }
            cbb.setItems(listven);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickTable(MouseEvent event) {
        product p = tableproductview.getSelectionModel().getSelectedItem();
        txtcodepro.setText(p.getItemCode());
        txtname.setText(p.getNamepro());
        cbb.setValue(p.getVendorname());
        txtdes.setText(p.getDescription());
        txtsize.setText(p.getSize());
        txtprice.setText(String.valueOf(p.getPrice()));
        txtqty.setText(String.valueOf(p.getQty()));
        txtbatch.setText(p.getBatchid());
    }

    @FXML
    private void SearchAction(ActionEvent event) {
        try {
            productDao proDao = new productDao();
            if (txtname.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Key Word ");
                alert.showAndWait();
            } else{ 
                product prod = proDao.searchPro(txtname.getText());
                if (prod != null) {
                txtcodepro.setText(prod.getItemCode());
                txtname.setText(prod.getNamepro());
                cbb.setValue(prod.getVen().getVendorname());
                txtdes.setText(prod.getDescription());
                txtsize.setText(prod.getSize());
                txtprice.setText(String.valueOf(prod.getPrice()));
                txtqty.setText(String.valueOf(prod.getQty()));
                txtbatch.setText(prod.getBatchid());
                String tilte = "Product Searched ";
                String message = "Product Is " + "" + txtname.getText() + "";
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            } else {
                String tilte = "Searched Product Not Found";
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
            product pro = new product();
            pro.setItemCode(txtcodepro.getText());
            pro.setNamepro(txtname.getText());
            vendorlot ven = venDao.findByName(cbb.getValue());
            pro.setDescription(txtdes.getText());
            pro.setSize(txtsize.getText());
            pro.setPrice(Double.parseDouble(txtprice.getText()));
            pro.setQty(Integer.parseInt(txtqty.getText()));
            pro.setBatchid(txtbatch.getText());
            pro.setVen(ven);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            productDao proDao = new productDao();
            if (proDao.insert(pro)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Product Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Product Is Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showPro();

            } else {
                (new Alert(Alert.AlertType.ERROR, "Product Not Added ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Product Is Not Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Product Is Already On The Sever";
            String message = "Product Is Not Added";
            tray.notification.TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        }
        showPro();
    }

    @FXML
    private void UpdateAction(ActionEvent event) throws Exception {
        try {
            product pro = new product();
            pro.setItemCode(txtcodepro.getText());
            pro.setNamepro(txtname.getText());
            vendorlot ven = venDao.findByName(cbb.getValue());
            pro.setDescription(txtdes.getText());
            pro.setSize(txtsize.getText());
            pro.setPrice(Double.parseDouble(txtprice.getText()));
            pro.setQty(Integer.parseInt(txtqty.getText()));
            pro.setBatchid(txtbatch.getText());
            pro.setVen(ven);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            productDao proDao = new productDao();
            if (proDao.update(pro)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Product Update Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Successful";
                message = "Product Is Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showPro();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Product Not Updated ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Updated Un Successful";
                message = "Product Is Not Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Product Is Already On The Sever";
            String message = "product Is Not Updated";
            tray.notification.TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }


    @FXML
    private void ClearAction(ActionEvent event) {
        if (event.getSource() == btnclear) {
            ClearText();
        }
    }

    @FXML
    private void vendorAction(ActionEvent event) {
    }
}
