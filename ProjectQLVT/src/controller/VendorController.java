/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.vendorDao;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class VendorController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<vendorlot> tblven;
    @FXML
    private TableColumn<vendorlot, String> tblname;
    @FXML
    private TableColumn<vendorlot, String> tblphone;
    @FXML
    private TableColumn<vendorlot, String> tbladdress;
    @FXML
    private TableColumn<vendorlot, String> tblemail;
    @FXML
    private TableColumn<vendorlot, String> tbldelete;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtaddress;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtphone;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btninsert;
    @FXML
    private JFXButton btnup;
    @FXML
    private TextField txtcodeven;
    @FXML
    private JFXButton btnclear;
    vendorDao venDao = new vendorDao();
    PreparedStatement preparedStatement = null;
    String query = null;
    vendorlot ven = null;
    Connection connection = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showVen();
    }    
    public void showVen(){
        ObservableList<vendorlot> list = venDao.findAll();
        tblname.setCellValueFactory(new PropertyValueFactory<>("vendorname") );
        tblphone.setCellValueFactory(new PropertyValueFactory<>("vendorphone") );
        tbladdress.setCellValueFactory(new PropertyValueFactory<>("vendoraddress") );
        tblemail.setCellValueFactory(new PropertyValueFactory<>("vendoremail") );
        tblven.setItems(list);
        Callback<TableColumn<vendorlot, String>, TableCell<vendorlot, String>> cellFoctory = (TableColumn<vendorlot, String> param) -> {
            final TableCell<vendorlot, String> cell = new TableCell<vendorlot, String>() {

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
                                ven = tblven.getSelectionModel().getSelectedItem();
                                query = "delete from product where vendorID='" + ven.getVendorID() + "'";
                                connection = DBConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                showVen();
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
        tblven.setItems(list);
    }
    @FXML
    private void clickTable(MouseEvent event) {
        vendorlot ven = tblven.getSelectionModel().getSelectedItem();
        txtcodeven.setText(ven.getVendorID());
        txtname.setText(ven.getVendorname());
        txtphone.setText(ven.getVendorphone());
        txtaddress.setText(ven.getVendoraddress());
        txtemail.setText(ven.getVendoremail());
    }

    @FXML
    private void SearchAction(ActionEvent event) {
        try {
             vendorlot ven = new vendorlot();
            if (txtname.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Key Word ");
                alert.showAndWait();
            } else{ 
                vendorlot v = venDao.findByName(txtname.getText());
                if (v != null) {
                txtcodeven.setText(v.getVendorID());
                txtname.setText(v.getVendorname());
                txtphone.setText(v.getVendorphone());
                txtaddress.setText(v.getVendoraddress());
                txtemail.setText(v.getVendoremail());
                String tilte = "Vendor Searched ";
                String message = "Vendor Is " + "" + txtname.getText() + "";
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            } else {
                String tilte = "Searched Vendor Not Found";
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
            vendorlot ven = new vendorlot();
            ven.setVendorID(txtcodeven.getText());
            ven.setVendorname(txtname.getText());
            ven.setVendorphone(txtphone.getText());
            ven.setVendoraddress(txtaddress.getText());
            ven.setVendoremail(txtemail.getText());
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            if(venDao.insert(ven)){
                (new Alert(Alert.AlertType.CONFIRMATION, "Vendor Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Vendor Is Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showVen();
            } else{
                 (new Alert(Alert.AlertType.ERROR, "Vendor Not Added ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Vendor Is Not Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
             tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Vendor Is Already On The Sever";
            String message = "Vendor Is Not Added";
            tray.notification.TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        }
        showVen();
    }

    @FXML
    private void UpdateAction(ActionEvent event) throws Exception {
        try{
        vendorlot ven = new vendorlot();
            ven.setVendorID(txtcodeven.getText());
            ven.setVendorname(txtname.getText());
            ven.setVendorphone(txtphone.getText());
            ven.setVendoraddress(txtaddress.getText());
            ven.setVendoremail(txtemail.getText());
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            if(venDao.update(ven)){
                (new Alert(Alert.AlertType.CONFIRMATION, "Vendor Updated Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Successful";
                message = "Vendor Is Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showVen();
            } else{
                 (new Alert(Alert.AlertType.ERROR, "Vendor Not Updated ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Updated Un Successful";
                message = "Vendor Is Not Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
             tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Vendor Is Already On The Sever";
            String message = "Vendor Is Not Updated";
            tray.notification.TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        }
        showVen();
    }

    @FXML
    private void ClearAction(ActionEvent event) {
        txtname.clear();
        txtphone.clear();
        txtaddress.clear();
        txtemail.clear();
        txtcodeven.clear();
    }
    
}
