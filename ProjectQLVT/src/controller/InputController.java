/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.inputDao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.InTM;
import model.InTM2;
import model.input;
import model.owe;
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
public class InputController implements Initializable {

    @FXML
    private Pane order;
    @FXML
    private Label txtdate;
    @FXML
    private Label txtinput;
    @FXML
    private Label txtid;
    private JFXComboBox<String> cbb;
    @FXML
    private Label txtto;
    @FXML
    private TextField txtqty;
    @FXML
    private TextField txtprice;
    @FXML
    private JFXButton btnok;
    @FXML
    private Label txttt;
    @FXML
    private TableView<InTM> tblin;
    @FXML
    private TableColumn<InTM, String> tblcode;
    @FXML
    private TableColumn<InTM, Double> tblprice;
    @FXML
    private TableColumn<InTM, Integer> tblamount;
    @FXML
    private TableColumn<InTM, Double> tbltotal;
    @FXML
    private JFXButton btninput;
    @FXML
    private JFXButton btnremove;
    @FXML
    private Label labelemp;
    @FXML
    private ImageView request;
    int count;
    ArrayList<input> items = new ArrayList<>();
    ArrayList<InTM> items2 = new ArrayList<>();
    ArrayList<InTM2> items3 = new ArrayList<>();
    inputDao inDao = new inputDao();
    input input;
    InTM InTM;
    @FXML
    private TextField txtid2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        generateDateTime();
        settxtoweid();
        showIn();
    }
    public void showIn(){
        tblcode.setCellValueFactory(new PropertyValueFactory<>("IDProduct"));
        tblprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        tblamount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        tbltotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
    }
    public void generateDateTime() {
        this.txtdate.setText(LocalDate.now().toString());
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

    public int getRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(InputID) FROM input";
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
                this.txtinput.setText("L00" + (id + 1));
            } else if (id < 99) {
                this.txtinput.setText("L0" + (id + 1));
            } else {
                this.txtinput.setText("L" + (id + 1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    double total = 0;
    private boolean up(){
        String errorMessage = "";
        count = Integer.parseInt(txtqty.getText());
        if (txtqty.getText() == null || txtqty.getText().length() == 0) {
            errorMessage += "Quantity not supplied!";
        }
        return false;
    }
    @FXML
    private void OKAction(ActionEvent event) {
        try {
            
            if(up()){
            count = Integer.parseInt(txtqty.getText());
            total = count * Double.parseDouble(txtprice.getText());
            txttt.setText(String.valueOf(total));
            txtto.setText(count + "");
            String IDProduct = txtid2.getText();
            Double Price = Double.parseDouble(txtprice.getText());
            Integer Amount = Integer.parseInt(txtqty.getText());
            Double Total = Double.parseDouble(txttt.getText());
            InTM rowData = new InTM(IDProduct, Price, Amount,Total);
            items2.add(rowData);
            tblin.setItems(FXCollections.observableArrayList(items2)); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickTable(MouseEvent event) {
    }

    @FXML
    private void InputAction(ActionEvent event) {
        String InputID = txtinput.getText();
        String DateInvoicee = txtdate.getText();
        double Total = Double.parseDouble(txttt.getText());
        Integer Amount = Integer.parseInt(txtqty.getText());
        double Price = Double.parseDouble(txtprice.getText());
        String IDProduct =txtid2.getText();
        String ProductID =txtid2.getText();
       Integer Inventory = Integer.parseInt(txtto.getText());
        Integer Amountinput = Integer.parseInt(txtqty.getText());
        String Dateinput = txtdate.getText();
        double OriginalPrice = Double.parseDouble(txtprice.getText());
        try {
 //           boolean printAndSave = inDao.placeOrder(new input(InputID, DateInvoicee, Total, Amount, Price, IDProduct, ProductID, Inventory, Amountinput, Dateinput, OriginalPrice));
               boolean printAndSave = inDao.placeOrder2(new input(InputID, DateInvoicee, Total, items2));
            if (printAndSave) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Input Successfully", new ButtonType[]{ButtonType.OK})).show();
                String tilte = "Input SUCCESS";
                String message = "Input SUCCESS";
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                settxtoweid();

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));

            } else {
                (new Alert(Alert.AlertType.ERROR, "Input Unsuccessfully", new ButtonType[]{ButtonType.OK})).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        int selectedIndex = tblin.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            tblin.getItems().remove(selectedIndex);
            items.remove(selectedIndex);

        }
    }

    @FXML
    private void reloadAction(MouseEvent event) {
        txtprice.setText("");
        txtqty.setText("");
        tblcode.setText("");
        tblprice.setText("");
        tblamount.setText("");
        tbltotal.setText("");
        txtid2.setText("");
    }

    @FXML
    private void adddiscountKey(KeyEvent event) {
    }

    @FXML
    private void WareAction(MouseEvent event) {
          try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Inventory.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OrdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
