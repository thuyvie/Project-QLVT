/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import Dao.payowe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import model.dtm;
import model.order2;
import model.owe;
import model.payment;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import util.CrudUtil;
import util.DBConnect;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author Mun Chan
 */
public class ShowPaymentController implements Initializable {

    @FXML
    private Pane order;
    @FXML
    private Label txtdate;
    @FXML
    private Label txttime;
    private TextField txtowe;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtaddress;
    @FXML
    private TextField txtsearchphone;
    @FXML
    private TextField txtemail;
    @FXML
    private JFXButton btnsearchphone;
    @FXML
    private TextField txtamount;
    @FXML
    private TextField txtnote;
    @FXML
    private JFXButton btnsearch;
    @FXML
    private JFXButton btnsave;
    @FXML
    private TextField txtpaid;
    @FXML
    private Pane discount;
    @FXML
    private Label txttotal;
    @FXML
    private Label txtpa;
    @FXML
    private Label txtrs;
    @FXML
    private Label txto;
    @FXML
    private TableView<dtm> tblre;
    @FXML
    private TableColumn<dtm, String> tblname;
    @FXML
    private TableColumn<dtm, String> tblform;
    @FXML
    private TableColumn<dtm, String> tblpaid;
    @FXML
    private TableColumn<dtm, String> tblowe;
    @FXML
    private JFXButton btnprint;
    @FXML
    private JFXButton btnremove;
    @FXML
    private ImageView request;
    @FXML
    private TextField txtor;
    @FXML
    private TextField txtcode;
    @FXML
    private TextField txtnamecus;
    double count;
    int total;
    private int index;
   ArrayList<dtm> items = new ArrayList<>();
   payowe PY = new payowe();
   owe ow = new owe();
    dtm dTM;
    @FXML
    private TableColumn<dtm, String> tbltt;
    private JFXCheckBox txtfull;
    private JFXCheckBox txtun;
    ObservableList<String> checkBookList = FXCollections.observableArrayList();
    private TextField txtpaidfull;
    @FXML
    private JFXCheckBox txtfull2;
    @FXML
    private JFXCheckBox txtun2;
    @FXML
    private JFXButton btnshowdata;
    @FXML
    private Label txtoweid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        settxtoweid();
        generateDateTime();
        showPayment();
    }    
    public void generateDateTime() {
        this.txtdate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, (e) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            this.txttime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1.0D)));
        timeline.setCycleCount(-1);
        timeline.play();

    }
    public void showPayment() {
        tblname.setCellValueFactory(new PropertyValueFactory<>("namecus"));
        tblform.setCellValueFactory(new PropertyValueFactory<>("paymentform"));
        tblpaid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        tblowe.setCellValueFactory(new PropertyValueFactory<>("owe"));
        tbltt.setCellValueFactory(new PropertyValueFactory<>("TotalDebt"));
    }
    @FXML
    private void searchAction2(ActionEvent event) {
        try {
            payowe oDao = new payowe();
            if (txtsearchphone.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Key Word ");
                alert.showAndWait();
            } else {
                order2 or = oDao.searchCus(txtsearchphone.getText());
                if (or != null) {
                    txtname.setText(or.getNameCus());
                    txtaddress.setText(or.getAddressCus());
                    txtsearchphone.setText(or.getPhoneCus());
                    txtemail.setText(or.getEmailCus());
                    txtor.setText(or.getOrdID());
                } else {
                    String tilte = "Searched Order Infor Not Found";
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
    private void searchAction1(ActionEvent event) {
         try {
            payowe oDao = new payowe();
            if ( txtcode.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please Enter Key Word ");
                alert.showAndWait();
            } else {
                payment pay = oDao.searchPay(txtcode.getText());
                if (pay != null) {
                    txtcode.setText(pay.getOrdID());
                    txtamount.setText(String.valueOf(pay.getAmount()));
                    txttotal.setText(String.valueOf(pay.getAmount()));
                    txtnote.setText(String.valueOf(pay.getNote()));
                    txtnamecus.setText(pay.getNameCus());
                } else {
                    String tilte = "Searched Payment Infor Not Found";
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
    private void reloadPaid() {
        double pai = 0;
        int total = 0;
        double owe = 0;
        for (dtm dtm : items) {
            pai += Double.parseDouble(dtm.getPaid());
            double p = Double.parseDouble(dtm.getAmount());
            owe += p - Double.parseDouble(dtm.getPaid());
        }
       txtpa.setText(pai + "");
        txto.setText(owe + "");
    }
    private void finalTotaladd() {
        txtrs.setText(txto.getText());
    }
    @FXML
    private void SaveAction(ActionEvent event) {
        count += Double.parseDouble(txtpaid.getText());
        txtpa.setText(count + "");
        double total = 0;
        
        total = Double.parseDouble(txtamount.getText()) - count;
        
        txto.setText(String.valueOf(total));
        finalTotaladd();
        settxtoweid();
    }

    @FXML
    private void clickTable(MouseEvent event) {
        dtm c = tblre.getSelectionModel().getSelectedItem();
        txtnamecus.setText(c.getNamecus());
        txtnote.setText(c.getPaymentform());
    }


    @FXML
    private void deleteAction(ActionEvent event) {
        items.remove(index);
         tblre.setItems(FXCollections.observableArrayList(items));
          reloadPaid();
    }

    @FXML
    private void reloadAction(MouseEvent event) {
        txtname.setText("");
        txtaddress.setText("");
        txtsearchphone.setText("");
        txtemail.setText("");
        txttotal.setText("");
        txto.setText("");
        txtrs.setText("");
        txtoweid.setText("");
        txtsearchphone.setText("");
        txtpa.setText("");
        txtcode.setText("");
        txtor.setText("");
        txtnote.setText("");
        txtamount.setText("");
        txtnamecus.setText("");
    }
     public int getRowCount() throws ClassNotFoundException, SQLException {
        String SQL = "SELECT COUNT(IdOwe) FROM owe";
        ResultSet resultSet = CrudUtil.executeQuery(SQL);
        if (resultSet.next()){
            return resultSet.getInt(1);
        }
        return -1;
    }
    public void settxtoweid(){
        try {
             int id = getRowCount();
            if (id < 9) {
                this.txtoweid.setText("I00" + (id + 1));
            } else if (id < 99) {
                this.txtoweid.setText("I0" + (id + 1));
            } else {
                this.txtoweid.setText("I" + (id + 1));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public void oweBill(String child){
//         try {
//            InputStream is = this.getClass().getResourceAsStream("/report/Bill/owe.jrxml");
//            JasperReport jr = JasperCompileManager.compileReport(is);
//            HashMap<String, Object> hs = new HashMap<>();
//               hs.put("child", child);
//            JasperPrint jp = JasperFillManager.fillReport(jr, hs, DBConnect.getConnect());
//            JasperViewer.viewReport(jp); 
//        } catch (JRException e) {
//            e.printStackTrace();
//        }
//    }    
      public void Print(String child){
        
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("child", child);
        
        try {
            InputStream is = this.getClass().getResourceAsStream("/report/Bill/bill.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);
            
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, DBConnect.getConnect());
            JasperViewer.viewReport(jp); 
            
//            JasperExportManager.exportReportToPdfStream(jp, new FileOutputStream(new File(System.getProperty("user.home")+File.separator+"challanreport.pdf")));
        } catch (JRException ex) {
            Logger.getLogger(ShowPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    private void printAndSaveAction(ActionEvent event) {
        String IdOwe = txtoweid.getText();
        String NameCus= txtname.getText();
        String PhoneCus = txtsearchphone.getText();
        String EmailCus = txtemail.getText();
        String AddressCus = txtaddress.getText();
        String PaymentForm = txtnote.getText();
        String status = "";
        if(txtfull2.isSelected()){
            status += txtfull2.getText();
        }else if(txtun2.isSelected()){
        status += txtun2.getText();
    }
        double Paid = Double.parseDouble(txtpa.getText());
        double Owe = Double.parseDouble(txto.getText());
        double TotalDebt = Double.parseDouble(txtrs.getText());
        try {
            boolean printAndSave = PY.placeOrder(new owe(IdOwe,NameCus,PhoneCus,EmailCus,AddressCus,PaymentForm,status,Paid,Owe,TotalDebt));
            Print(IdOwe);
            if(printAndSave){
            (new Alert(Alert.AlertType.CONFIRMATION, "OweReceipt Successfully", new ButtonType[]{ButtonType.OK})).show();
                String tilte = "OweReceipt SUCCESS";
                String message = "OweReceipt SUCCESS";
                tray.notification.TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
               settxtoweid();
              
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
    private void DataAction(ActionEvent event) {
        String namecus = txtnamecus.getText();
        String paymentform = txtnote.getText();
        String paid = txtpaid.getText();
        String owe = txto.getText();
        String TotalDebt = txtrs.getText();
        String aoumt = txtamount.getText();
        dtm rowData = new dtm(namecus, paymentform,paid,owe,TotalDebt);
        items.add(rowData);
        tblre.setItems(FXCollections.observableArrayList(items));
    }
    
}
