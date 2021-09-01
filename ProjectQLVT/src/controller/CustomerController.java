/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import model.customer;
import Dao.CusDao;
import javafx.scene.control.ButtonType;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.DBConnect;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class CustomerController implements Initializable {

    @FXML
    private TableView<customer> tablecusview;
    @FXML
    private TableColumn<customer, String> tblname;
    @FXML
    private TableColumn<customer, String> tblphone;
    @FXML
    private TableColumn<customer, String> tblemail;
    @FXML
    private TableColumn<customer, String> tbladdress;
    @FXML
    private TableColumn<customer,String> tbldelete;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtaddress;
    @FXML
    private TextField txtphone;
    @FXML
    private TextField txtemail;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btninsert;
    @FXML
    private JFXButton btnup;
    @FXML
    private JFXButton btnclear;

    /**
     * Initializes the controller class.
     */
    PreparedStatement preparedStatement = null;
    String query = null;
    customer customers = null;
    Connection connection = null;
    private boolean Save;
    private boolean isUpdate;
    @FXML
    private TextField txtid;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCus();
    }    
     public ObservableList<customer> findAll() {
        ObservableList<customer> listcus = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
             String sql = "SELECT  customer.NameCus,customer.PhoneCus,customer.EmailCus,customer.AddressCus, customer.ID FROM customer ORDER BY ID DESC";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                customer cus = new customer();
                cus.setNameCus(rs.getString("NameCus"));
                cus.setPhoneCus(rs.getString("PhoneCus"));
                cus.setEmailCus(rs.getString("EmailCus"));
                cus.setAddressCus(rs.getString("AddressCus"));
                cus.setID(rs.getString("ID"));
                listcus.add(cus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listcus;
    }
     public void showCus() {
        ObservableList<customer> list = findAll();
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblemail.setCellValueFactory(new PropertyValueFactory<>("EmailCus"));
        tbladdress.setCellValueFactory(new PropertyValueFactory<>("AddressCus"));
        Callback<TableColumn<customer, String>, TableCell<customer, String>> cellFoctory = (TableColumn<customer, String> param) -> {
            final TableCell<customer, String> cell = new TableCell<customer, String>() {

                @Override
                public void updateItem(String customer, boolean empty) {
                    super.updateItem(customer, empty);
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
                                customers = tablecusview.getSelectionModel().getSelectedItem();
                                query = "delete from customer where ID='" + customers.getID() + "'";
                                connection = DBConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                showCus();
                            } catch (SQLException ex) {
                                Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
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
        tablecusview.setItems(list);
    }
     
        private void ClearText() {
        txtname.setText(null);
        txtphone.getText();
        txtemail.setText(null);
        txtaddress.setText(null);
        }

    @FXML
    private void clickTable(MouseEvent event) {
        customer cus = tablecusview.getSelectionModel().getSelectedItem();
        txtname.setText(cus.getNameCus());
        txtphone.setText(cus.getPhoneCus());
        txtemail.setText(cus.getEmailCus());
        txtaddress.setText(cus.getAddressCus());
        txtid.setText(cus.getID());
    }

    @FXML
    private void SearchAction(ActionEvent event) {
        try {
            CusDao CusDao = new CusDao();
            if (txtphone.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Phone ");
                alert.showAndWait();
            } else{ 
                customer cus = CusDao.searchCus(txtname.getText());
                if (cus != null) {
                txtname.setText(cus.getNameCus());
                txtemail.setText(cus.getEmailCus());
                txtaddress.setText(cus.getAddressCus());
                txtphone.setText(cus.getPhoneCus());
                String tilte = "Customer Searched ";
                String message = "Customer Is " + "" + txtphone.getText() + "";
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            } else {
                String tilte = "Searched Customer Not Found";
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
            customer cus = new customer();
            cus.setNameCus(txtname.getText());
            cus.setPhoneCus(txtphone.getText());
            cus.setEmailCus(txtemail.getText());
            cus.setAddressCus(txtaddress.getText());
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            CusDao CusDao = new CusDao();
            if (CusDao.insert(cus)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Customer Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Customer Is Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showCus();

            } else {
                (new Alert(Alert.AlertType.ERROR, "Customer Not Added ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Custoomer Is Not Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Account Customer Is Already On The Sever";
            String message = "Customer Is Not Added";
            tray.notification.TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        }
        showCus();
    }

    @FXML
    private void UpdateAction(ActionEvent event) throws Exception {
        try {
           customer cus = new customer();
           cus.setID(txtid.getText());
           cus.setNameCus(txtname.getText());
           cus.setAddressCus(txtaddress.getText());
           cus.setEmailCus(txtemail.getText());
            cus.setPhoneCus(txtphone.getText());
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            CusDao cusDao = new CusDao();
            if (cusDao.update(cus)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Customer Update Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Successful";
                message = "Customer Is Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showCus();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Customer Not Updated ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Updated Un Successful";
                message = "Customer Is Not Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Customer Is Already On The Sever";
            String message = "Customer Is Not Updated";
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
    
}
