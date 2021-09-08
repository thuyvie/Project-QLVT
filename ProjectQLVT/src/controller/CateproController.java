/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.CateProDao;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.catepro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.CrudUtil;
import util.DBConnect;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class CateproController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<catepro> tblcate;
    @FXML
    private TableColumn<catepro, String> tblname;
    @FXML
    private TableColumn<catepro, String> tbldelete;
    @FXML
    private TextField txtname;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btninsert;
    @FXML
    private JFXButton btnup;
    @FXML
    private Label txtid;
    @FXML
    private JFXButton btnclear;

    /**
     * Initializes the controller class.
     */
    PreparedStatement preparedStatement = null;
    String query = null;
    catepro cp = null;
    Connection connection = null;
    CateProDao cpDao = new CateProDao();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCate();
        settxtoweid();
    }

    public int getRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(ID) FROM cateproduct";
        ResultSet resultSet = CrudUtil.executeQuery(SQL);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    public void settxtoweid() {
        try {
            int id = getRowCount();
            if (id < 9) {
                this.txtid.setText((id + 1) + "");
            } else if (id < 99) {
                this.txtid.setText((id + 1) + "");
            } else {
                this.txtid.setText((id + 1) + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCate() {
        ObservableList<catepro> list = cpDao.findAll();
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCate"));
        tblcate.setItems(list);
        Callback<TableColumn<catepro, String>, TableCell<catepro, String>> cellFoctory = (TableColumn<catepro, String> param) -> {
            final TableCell<catepro, String> cell = new TableCell<catepro, String>() {

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
                                cp = tblcate.getSelectionModel().getSelectedItem();
                                query = "delete from cateproduct where ID=" + cp.getID() + "";
                                connection = DBConnect.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                showCate();
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
        tblcate.setItems(list);
    }

    @FXML
    private void clickTable(MouseEvent event) {
        catepro cp = tblcate.getSelectionModel().getSelectedItem();
        txtid.setText(String.valueOf(cp.getID()));
        txtname.setText(cp.getNameCate());
    }

    @FXML
    private void SearchAction(ActionEvent event) {
        try {
            catepro cp = new catepro();
            if (txtname.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Key Word ");
                alert.showAndWait();
            } else {
                catepro c = cpDao.findByName(txtname.getText());
                if (c != null) {
                    txtid.setText(String.valueOf(c.getID()));
                    txtname.setText(c.getNameCate());
                    String tilte = "Category Name Searched ";
                    String message = "Category Name  Is " + "" + txtname.getText() + "";
                    tray.notification.TrayNotification tray = new TrayNotification();
                    settxtoweid();
                    AnimationType type = AnimationType.POPUP;

                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));
                } else {
                    String tilte = "Searched Category Name  Not Found";
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
            catepro cp = new catepro();
            cp.setNameCate(txtname.getText());
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            if (cpDao.insert(cp)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Category Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Category Is Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                showCate();
                settxtoweid();
                txtname.clear();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Category Not Added ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Category Is Not Added";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Category Is Already On The Sever";
            String message = "Category Is Not Added";
            tray.notification.TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        }
        showCate();
    }

    @FXML
    private void UpdateAction(ActionEvent event) throws Exception {
        try {
            catepro cp = new catepro();
            cp.setID(txtid.getText());
            cp.setNameCate(txtname.getText());
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            if (cpDao.update(cp)) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Category Updated Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Updated Successful";
                message = "Category Is Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                settxtoweid();
                showCate();
                txtname.clear();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Category Not Updated ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Updated Un Successful";
                message = "Category Is Not Updated";
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
            }
            tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Category Is Already On The Sever";
            String message = "Category Is Not Updated";
            tray.notification.TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(3000));
        }
        showCate();
    }

    @FXML
    private void ClearAction(ActionEvent event) {
        txtname.clear();
    }

}
