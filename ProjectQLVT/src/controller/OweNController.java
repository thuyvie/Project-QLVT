/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Dao.payowe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.detailOwe;
import model.owe;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.DBConnect;
import java.sql.PreparedStatement;

/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class OweNController implements Initializable {

    @FXML
    private Pane order;
    @FXML
    private Label txtoweid;
    @FXML
    private JFXButton btnsave;
    @FXML
    private TextField txtpaid;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtphone;
    @FXML
    private Pane discount;
    @FXML
    private TextField txttotal;
    @FXML
    private Label txtrs;
    @FXML
    private JFXCheckBox txtfull2;
    @FXML
    private JFXCheckBox txtun2;
    @FXML
    private JFXButton btnprint;
    @FXML
    private ImageView request;
    @FXML
    private TextField txtpaid2;
    @FXML
    private Label txttotaldebt;
    @FXML
    private Label txtpai;
    @FXML
    private Label txtowe;
    @FXML
    private TableColumn<detailOwe, String> tblow;
    @FXML
    private TableColumn<detailOwe, String> tblname;
    @FXML
    private TableColumn<detailOwe, String> tblphone;
    @FXML
    private TableColumn<detailOwe, String> tblmoney;
    @FXML
    private TableColumn<detailOwe, String> tbltt;
    @FXML
    private TableView<detailOwe> tblN;
    double count;
    int total;
    private int index;
    private boolean OWE;
     String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    void setOWE(boolean o){
        this.OWE = o;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showN();
    }    
    public ObservableList<detailOwe> findAll() {
        ObservableList<detailOwe> listow = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet rs;
        try {
            String sql = "SELECT NameCus, PhoneCus, Paid, TotalDebt, IdOwe FROM detailowe WHERE PhoneCus";
            Connection con = DBConnect.getConnect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                detailOwe ow= new detailOwe();
                ow.setNameCus(rs.getString("NameCus"));
                ow.setPhoneCus(rs.getString("PhoneCus"));
                ow.setPaid(rs.getDouble("Paid"));
                ow.setTotalDebt(rs.getDouble("TotalDebt"));
                ow.setIdOwe(rs.getString("IdOwe"));
                listow.add(ow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listow;
    }
    void setTextField(String NameCus, String PhoneCus, Double Paid, Double TotalDebt,String IDdOwe){
        txtname.setText(NameCus);
        txtphone.setText(PhoneCus);
        txtpaid.setText(String.valueOf(Paid));
        txttotal.setText(String.valueOf(TotalDebt));
        txttotaldebt.setText(String.valueOf(TotalDebt));
        txtoweid.setText(IDdOwe);
    }
    public void showN(){
        ObservableList<detailOwe> listow = findAll();
        tblow.setCellValueFactory(new PropertyValueFactory<>("IdOwe"));
        tblname.setCellValueFactory(new PropertyValueFactory<>("NameCus"));
        tblphone.setCellValueFactory(new PropertyValueFactory<>("PhoneCus"));
        tblmoney.setCellValueFactory(new PropertyValueFactory<>("Paid"));
        tbltt.setCellValueFactory(new PropertyValueFactory<>("TotalDebt"));
        tblN.setItems(listow);
    }
    private void finalTotaladd() {
        txtrs.setText(txtowe.getText());
        
    }
    @FXML
    private void SaveAction(ActionEvent event) {
        count += Double.parseDouble(txtpaid2.getText());
        txtpai.setText(count + "");
        double total = 0;
        
        total = Double.parseDouble(txttotal.getText()) - count;
        
        txtowe.setText(String.valueOf(total));
        finalTotaladd();
    }


    @FXML
    private void printAndSaveAction(ActionEvent event) {
        payowe P = new payowe();
        String IdOwe = txtoweid.getText();
        String NameCus= txtname.getText();
        String PhoneCus = txtphone.getText();
        String status = "";
        if(txtfull2.isSelected()){
            status += txtfull2.getText();
        }else if(txtun2.isSelected()){
        status += txtun2.getText();
        }
        double Paid = Double.parseDouble(txtpai.getText());
        double Owe = Double.parseDouble(txtowe.getText());
        double TotalDebt = Double.parseDouble(txtrs.getText());
         try {
            boolean printAndSave = P.placeOrder2(new owe(IdOwe,NameCus,PhoneCus,status,Paid,Owe,TotalDebt));
            if(printAndSave){
            (new Alert(Alert.AlertType.CONFIRMATION, "OweReceipt Successfully", new ButtonType[]{ButtonType.OK})).show();
                String tilte = "OweReceipt SUCCESS";
                String message = "OweReceipt SUCCESS";
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                
        }else{
            (new Alert(Alert.AlertType.ERROR, "OweReceipt Unsuccessfully", new ButtonType[]{ButtonType.OK})).show();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void reloadAction(MouseEvent event) {
        txtname.setText("");
        txtphone.setText("");
        txtowe.setText("");
        txtoweid.setText("");
        txtpai.setText("");
        txtpaid.setText("");
        txtpaid2.setText("");
        txtrs.setText("");
        txttotal.setText("");
        txttotaldebt.setText("");
    }

    @FXML
    private void clickTB(MouseEvent event) {
        detailOwe de = tblN.getSelectionModel().getSelectedItem();
        txtoweid.setText(de.getIdOwe());
        txtname.setText(de.getNameCus());
        txtphone.setText(de.getPhoneCus());
        txtpaid.setText(String.valueOf(de.getPaid()));
        txttotal.setText(String.valueOf(de.getTotalDebt()));
        txttotaldebt.setText(String.valueOf(de.getTotalDebt()));
    }

    
}
